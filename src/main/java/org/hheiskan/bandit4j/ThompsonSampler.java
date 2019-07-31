package org.hheiskan.bandit4j;

import org.apache.commons.math3.distribution.BetaDistribution;

import java.util.ArrayList;
import java.util.List;

public class ThompsonSampler {
    private List<Double> a;
    private List<Double> b;

    public ThompsonSampler(int variants) {
        a = new ArrayList<>();
        b = new ArrayList<>();
        init(a, variants, 1);
        init(b, variants, 1);
    }

    public int getK() {
        double max = 0;
        int idx = 0;

        for (int i = 0; i < a.size(); i++) {
            BetaDistribution beta = new BetaDistribution(a.get(i), b.get(i));
            double current = beta.sample();
            if (current > max) {
                max = current;
                idx = i;
            }
        }

        return idx;
    }

    public void bulkUpdate(int idx, int tries, int successCount) {
        a.set(idx, a.get(idx) + successCount);
        b.set(idx, b.get(idx) + (tries - successCount));
    }

    public void update(int idx, boolean reward) {
        int r = reward ? 1 : 0;
        bulkUpdate(idx, 1, r);
    }

    private static void init(List<Double> arr, int variants, double number) {
        for (int i = 0; i < variants; i++) {
            arr.add(number);
        }
    }
}
