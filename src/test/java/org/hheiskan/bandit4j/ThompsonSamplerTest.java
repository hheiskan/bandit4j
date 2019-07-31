package org.hheiskan.bandit4j;

import org.junit.Test;

import static org.junit.Assert.*;


public class ThompsonSamplerTest {
    @Test
    public void testSampler() {
        ThompsonSampler sampler = new ThompsonSampler(3);
        int[] counters = {0, 0, 0};
        double[] weights = {0.1, 0.4, 0.5};

        for (int i = 0; i < 10000; i++) {
            int k = sampler.getK();
            counters[k]++;
            boolean reward = Math.random() < weights[k];
            sampler.update(k, reward);
        }

        System.out.println("Item with biggest weight should get most of the traffic");
        System.out.println(weights[0] + ", " + weights[1] + ", " + weights[2]);
        System.out.println(counters[0] + ", " + counters[1] + ", " + counters[2]);
        assertTrue(counters[0] < counters[1] && counters[1] < counters[2]);
    }

}