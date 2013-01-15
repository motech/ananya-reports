package org.motechproject.ananya.reports.testdata;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomUnitTestRunner  extends BlockJUnit4ClassRunner {
    private final long totalSubscriptions = 1L;

    public RandomUnitTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        List<FrameworkMethod> methods = super.computeTestMethods();
        if(methods.size() > totalSubscriptions) return  methods;

        ArrayList<FrameworkMethod> allMethods = new ArrayList<>();
        for(int i=0; i < totalSubscriptions / methods.size() ;i++){
            allMethods.addAll(methods);
        }
        Collections.shuffle(allMethods);
        return allMethods;
    }
}
