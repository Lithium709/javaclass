package com.company.crossover.test;

class TaskBase {
    int getStatusCode(Object obj) throws NullPointerException {
        if (obj != null ) {
            return 1;
        } else {
            return 0;
        }
    }
}



class ParTask extends TaskBase {


    @Override
    int getStatusCode(Object obj) throws ArithmeticException {
        return super.getStatusCode(obj);
    }
}