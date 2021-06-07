package com.melidh.desafiospring.resources.filters;

public class PromoFilter {

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return true;

        return !((boolean) obj);
    }
}
