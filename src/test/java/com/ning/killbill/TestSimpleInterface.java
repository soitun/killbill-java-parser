package com.ning.killbill;

import java.util.List;

import org.testng.annotations.Test;

import com.ning.killbill.objects.Field;
import com.ning.killbill.objects.ClassEnumOrInterface;
import com.ning.killbill.objects.Method;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TestSimpleInterface extends TestBase {

    @Test(groups = "fast")
    public void testSimpleInterface() {
        assertEquals(listener.getPackageName(), "com.ning.billing.account.api");
        final List<ClassEnumOrInterface> classesorInterfaces = listener.getAllClassesEnumOrInterfaces();
        assertEquals(classesorInterfaces.size(), 1);

        final ClassEnumOrInterface testInterface = classesorInterfaces.get(0);
        assertEquals(testInterface.getName(), "Account");

        assertEquals(testInterface.getSuperInterfaces().size(), 3);
        assertTrue(isSuperInterfaceDefined("com.ning.billing.junction.api.Blockable", testInterface.getSuperInterfaces()));
        assertTrue(isSuperInterfaceDefined("com.ning.billing.util.entity.Entity", testInterface.getSuperInterfaces()));
        assertTrue(isSuperInterfaceDefined("com.ning.billing.account.api.AccountData", testInterface.getSuperInterfaces()));

        assertEquals(testInterface.isInterface(), true);
        assertEquals(testInterface.isEnum(), false);
        assertEquals(testInterface.isClass(), false);

        assertEquals(testInterface.getMethods().size(), 2);
        Method toMutableAccountData = getMethod("toMutableAccountData", testInterface.getMethods());
        assertNotNull(toMutableAccountData);
        assertFalse(toMutableAccountData.isGetter());
        assertEquals(toMutableAccountData.getOrderedArguments().size(), 0);

        Method mergeWithDelegate = getMethod("mergeWithDelegate", testInterface.getMethods());
        assertNotNull(mergeWithDelegate);
        assertFalse(mergeWithDelegate.isGetter());
        assertEquals(mergeWithDelegate.getOrderedArguments().size(), 1);

        Field argument = mergeWithDelegate.getOrderedArguments().get(0);
        assertEquals(argument.getType(), "com.ning.billing.account.api.Account");
        assertEquals(argument.getName(), "delegate");
    }


    @Override
    public String getResourceName() {
        return "SimpleInterface";
    }

}
