package org.androidtransfuse.gen.componentBuilder;

import com.sun.codemodel.*;
import org.androidtransfuse.gen.UniqueVariableNamer;
import org.androidtransfuse.model.ComponentDescriptor;
import org.androidtransfuse.scope.ConcurrentDoubleLockingScope;
import org.androidtransfuse.scope.ContextScopeHolder;
import org.androidtransfuse.scope.Scope;

import javax.inject.Inject;

/**
 * @author John Ericksen
 */
public class ContextScopeComponentBuilder implements ComponentBuilder{

    private JCodeModel codeModel;
    private UniqueVariableNamer namer;

    @Inject
    public ContextScopeComponentBuilder(JCodeModel codeModel, UniqueVariableNamer namer) {
        this.codeModel = codeModel;
        this.namer = namer;
    }

    @Override
    public void build(JDefinedClass definedClass, ComponentDescriptor descriptor) {

        //setup context scope
        definedClass._implements(ContextScopeHolder.class);

        //scope variable
        JFieldVar scopeField = definedClass.field(JMod.PRIVATE, Scope.class, namer.generateName(Scope.class),
                JExpr._new(codeModel.ref(ConcurrentDoubleLockingScope.class)));

        //method
        JMethod getScope = definedClass.method(JMod.PUBLIC, Scope.class, ContextScopeHolder.GET_SCOPE);
        getScope.body()._return(scopeField);
    }
}
