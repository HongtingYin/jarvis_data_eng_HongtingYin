package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructure.map.HashJMap;

import java.util.Map;
import java.util.Set;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=18da95f2ee0e4aa397f2b260abf416d5
 */
public class MapComparison {

    /**
     * Big-O: O(n), where n is the number of key-value pairs
     *
     * @param m1 first map
     * @param m2 second map
     * @param <K> type of key
     * @param <V> type of value
     * @return true if they are equal, false otherwise
     */
    public static <K,V> boolean compareMapsAPI(Map<K, V> m1, Map<K, V> m2) {
        if (m1.equals(m2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Big-O: O(n), where n is the number of key-value pairs
     *
     * @param m1 first map
     * @param m2 second map
     * @param <K> type of key
     * @param <V> typle of value
     * @return true if they are equal, false otherwise
     */
    public static <K,V> boolean compareMapsHashJMap(Map<K, V> m1, Map<K, V> m2) {
        //HashJMap<K,V> hashJMap1 = (HashJMap) m1;
        //HashJMap<K,V> hashJMap2 = (HashJMap) m2;
        HashJMap<K,V> hashJMap1;
        hashJMap1 = (HashJMap<K, V>) m1;
        HashJMap<K,V> hashJMap2;
        hashJMap2 = (HashJMap<K, V>) m2;
        Set<Map.Entry<K,V>> entrySet1 = hashJMap1.entrySet();
        Set<Map.Entry<K,V>> entrySet2 = hashJMap2.entrySet();
        if (entrySet1.equals(entrySet2)) {
            return true;
        } else {
            return false;
        }
    }

}
