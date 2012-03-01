package org.androidtransfuse.integrationTest.inject;

import org.androidtransfuse.annotations.Activity;
import org.androidtransfuse.annotations.Layout;
import org.androidtransfuse.integrationTest.R;

import javax.inject.Inject;

/**
 * @author John Ericksen
 */
@Activity(name = "InjectionActivity")
@Layout(R.layout.main)
public class Injection {

    @Inject
    private InjectTarget privateInjection;
    @Inject
    protected InjectTarget protectedInjection;
    @Inject
    InjectTarget packagePrivateInjection;
    @Inject
    public InjectTarget publicInjection;
    private InjectTarget constructorInjection;
    private InjectTarget methodInjectionOne;
    private InjectTarget methodInjectionTwo;
    @Inject
    private LoopOne dependencyLoopOne;

    @Inject
    public Injection(InjectTarget constructorInjection) {
        this.constructorInjection = constructorInjection;
    }

    @Inject
    public void setMethodInjection(InjectTarget methodInjectionOne, InjectTarget methodInjectionTwo) {
        this.methodInjectionOne = methodInjectionOne;
        this.methodInjectionTwo = methodInjectionTwo;
    }

    public InjectTarget getPrivateInjection() {
        return privateInjection;
    }

    public InjectTarget getProtectedInjection() {
        return protectedInjection;
    }

    public InjectTarget getPackagePrivateInjection() {
        return packagePrivateInjection;
    }

    public InjectTarget getPublicInjection() {
        return publicInjection;
    }

    public InjectTarget getConstructorInjection() {
        return constructorInjection;
    }

    public InjectTarget getMethodInjectionOne() {
        return methodInjectionOne;
    }

    public InjectTarget getMethodInjectionTwo() {
        return methodInjectionTwo;
    }

    public LoopOne getDependencyLoopOne() {
        return dependencyLoopOne;
    }
}
