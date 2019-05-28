package by.aazqsc.home.in_action.core_3_aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience {

    // Перед выступлением
    public void takeSeats() {
        System.out.println("The audience is taking their seats.");
    }

    // Перед выступлением
    public void turnOffCellPhones() {
        System.out.println("The audience is turning off their cellphones");
    }

    // После выступления
    public void applaud() {
        System.out.println("CLAP CLAP CLAP CLAP CLAP");
    }

    // После неудачного выступления
    public void demandRefund() {
        System.out.println("Boo! We want our money back!");
    }

    public void watchPerformance(ProceedingJoinPoint joinpoint) {
        try {
            // Перед выступлением
            System.out.println("The audience is taking their seats.");
            System.out.println("The audience is turning off their cellphones");
            long start = System.currentTimeMillis();
            joinpoint.proceed();

            // Вызов целевого метода
            long end = System.currentTimeMillis();

            // После выступления
            System.out.println("CLAP CLAP CLAP CLAP CLAP");
            System.out.println("The performance took " + (end - start) + " milliseconds.");
        } catch (Throwable t) {
            System.out.println("Boo! We want our money back!");
        }
    }
}
