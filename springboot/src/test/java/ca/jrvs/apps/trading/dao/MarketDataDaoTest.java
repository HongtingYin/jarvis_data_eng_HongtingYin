package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MarketDataDaoTest {
    private MarketDataDao dao;

    @Before
    public void setUp() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(50);
        manager.setDefaultMaxPerRoute(50);
        MarketDataConfig config = new MarketDataConfig();
        config.setHost("https://cloud.iexapis.com/v1");
        config.setToken(System.getenv("IEX_PUB_TOKEN"));

        dao = new MarketDataDao(manager, config);
    }

    @Test
    public void findIexQuotesByTickers() throws IOException {
        List<IexQuote> quoteList = dao.findAllById(Arrays.asList("AAPL", "AMZN"));

        assertEquals(2, quoteList.size());
        assertEquals("AAPL", quoteList.get(0).getSymbol());
        assertEquals("AMZN", quoteList.get(1).getSymbol());

        try {
            dao.findAllById(Arrays.asList("AAPL?"));
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void findByTicker() {
        String ticker = "FB";
        IexQuote iexQuote = dao.findById(ticker).get();
        assertEquals(ticker, iexQuote.getSymbol());
    }
}