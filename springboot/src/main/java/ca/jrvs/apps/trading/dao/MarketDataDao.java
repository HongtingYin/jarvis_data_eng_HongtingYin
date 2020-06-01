package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * MarketDataDao is responsible for gettingQoutes from IEX
 */
public class MarketDataDao implements CrudRepository<IexQuote, String> {

    private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    private final String IEX_BATCH_URL;

    private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
    private HttpClientConnectionManager httpClientConnectionManager;

    public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
        this.httpClientConnectionManager = httpClientConnectionManager;
        IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
    }


    @Override
    public <S extends IexQuote> S save(S s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Get an IexQuote
     *
     * @param ticker
     * @throws IllegalArgumentException      if the given ticker is invalid
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    @Override
    public Optional<IexQuote> findById(String ticker) {
        Optional<IexQuote> iexQuote;
        List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

        if (quotes.size() == 0) {
            return Optional.empty();
        } else if (quotes.size() == 1) {
            iexQuote = Optional.of(quotes.get(0));
        } else {
            throw new DataRetrievalFailureException("Unexpected number of quotes");
        }
        return iexQuote;
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<IexQuote> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Get an IexQuote from IEX
     *
     * @param tickers is a list of tickers
     * @return a list of IexQuote object
     * @throws IllegalArgumentException      if any ticker is invalid or empty
     * @throws DataRetrievalFailureException if HTTP request failed
     */
    @Override
    public List<IexQuote> findAllById(Iterable<String> tickers) {
        List<IexQuote> quotes = new ArrayList<>();
        StringBuilder tickerStringList = new StringBuilder();

        for (String ticker : tickers) {
            tickerStringList.append(ticker);
            if (tickers.iterator().hasNext()) tickerStringList.append(",");
        }

        tickerStringList.deleteCharAt(tickerStringList.length() - 1);
        String URL = String.format(IEX_BATCH_URL, tickerStringList.toString().toUpperCase());

        try {
            Optional<String> response = executeHttpGet(URL);
            String responseEntity;

            if (response.isPresent()) {
                responseEntity = response.get();
                JSONObject quotesJson = new JSONObject(responseEntity);
                ObjectMapper mapper = new ObjectMapper();

                for (String ticker : tickers) {
                    if (!responseEntity.contains(ticker)) {
                        throw new IllegalArgumentException("Invalide tikcer: " + ticker);
                    }
                    String quoteJsonStr = quotesJson.getJSONObject(ticker).getJSONObject("quote").toString();
                    IexQuote quote = mapper.readValue(quoteJsonStr, IexQuote.class);
                    quotes.add(quote);
                }
            }
        } catch (IOException e) {
            throw new RuntimeJsonMappingException("Unable to map JSONObject to IexQuote");
        }
        return quotes;
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteById(String s) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void delete(IexQuote iexQuote) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends IexQuote> iterable) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Execute a get and return http entity as a string
     *
     * @param url resource URL
     * @return http response body or Optional.empty for 404 response
     * @throws DataRetrievalFailureException if HTTP failed, or status code is unexpected, or there is
     *                                       something wring with the response body
     */
    private Optional<String> executeHttpGet(String url) throws IOException {
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response;
        Optional<String> httpEntityString;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new DataRetrievalFailureException(e.getMessage(), e);
        }

        try {
            httpEntityString = Optional.ofNullable(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            throw new IOException("Unable to form HTTP entity from HTTP response" + e);
        }

        int status = response.getStatusLine().getStatusCode();
        if (status == HttpStatus.SC_OK) {
            return httpEntityString;
        } else if (status == HttpStatus.SC_NOT_FOUND) {
            return Optional.empty();
        } else {
            throw new IOException("Unexpected HTTP status: " + status);
        }
    }

    /**
     * Borrow a HTTP client from the httpClientConnectionManager
     */
    private CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(httpClientConnectionManager)
                //prevent connectionManager shut down when calling httpClient.close()
                .setConnectionManagerShared(true)
                .build();
    }
}
