package com.ning.killbill.objects;

import java.util.LinkedList;
import java.util.List;

public class Type {

    private final String baseType;
    private final String genericType;
    private final List<Type> genericSubTypes;

    public Type(final String baseType, final String genericType) {
        this(baseType, genericType, new LinkedList<Type>());

    }

    public Type(final String baseType, final String genericType, final List<Type> genericSubTypes) {
        this.baseType = baseType;
        this.genericType = genericType;
        this.genericSubTypes = genericSubTypes;
    }

    public Type(final String baseType) {
        this(baseType, null);
    }

    public String getBaseType() {
        return baseType;
    }

    public String getGenericType() {
        return genericType;
    }

    public List<Type> getGenericSubTypes() {
        return genericSubTypes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Type{");
        sb.append("baseType='").append(baseType).append('\'');
        sb.append(", genericType='").append(genericType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
