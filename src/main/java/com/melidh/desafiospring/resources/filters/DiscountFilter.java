package com.melidh.desafiospring.resources.filters;

public class DiscountFilter {

    @Override
    public boolean equals(Object obj) {
        if(obj == null || (Double) obj <= 0.0){
            return true;
        }

        return false;
    }
}
