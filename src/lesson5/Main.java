package lesson5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        met1(arr);
        met2(arr);
    }
    public static final int size = 12;
    public static final int thcount = 3;
    public static final int part = size/thcount;
    public static final int third = size/3;
    public static float[] arr = new float[size];

    public static void met1(float[] arr){
        long t1 = System.currentTimeMillis();
        Arrays.fill(arr, 1);
        long t2 = System.currentTimeMillis() - t1;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));

        }
        long t3 = System.currentTimeMillis() - t1 - t2;

        System.out.println("met1 start: " + t1 + " | " + t2 + " | " + t3);
        System.out.println(Arrays.toString(arr));

    }

    public static void met2(float[] arr){
        long t1 = System.currentTimeMillis();
        Arrays.fill(arr, 1);
        long t2 = System.currentTimeMillis() - t1;

        float[] arr1 = new float[part];
        float[] arr2 = new float[part];
        float[] arr3 = new float[part];

        System.arraycopy(arr, 0, arr1, 0, third);
        System.arraycopy(arr, third, arr2, 0, third);
        System.arraycopy(arr, third*2, arr3, 0, third);

        Thread th1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float)(arr1[i]*Math.sin(0.2f+i/5)*Math.cos(0.2f+i/5)*Math.cos(0.4f+i/2));
            }
        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                int f = part+i;
                arr2[i] = (float)(arr2[i]*Math.sin(0.2f+f/5)*Math.cos(0.2f+f/5)*Math.cos(0.4f+f/2));
            }
        });

        Thread th3 = new Thread(() -> {
            for (int i = 0; i < arr3.length; i++) {
                int f = part*2+i;
                arr3[i] = (float)(arr3[i]*Math.sin(0.2f+f/5)*Math.cos(0.2f+f/5)*Math.cos(0.4f+f/2));
            }
        });

        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.arraycopy(arr1, 0, arr, 0, third);
        System.arraycopy(arr2, 0, arr, third, third);
        System.arraycopy(arr3, 0, arr, third*2, third);

        long t3 = System.currentTimeMillis() - t1 - t2;
        System.out.println("met2 start: " + t1 + " | " + t2 + " | " + t3);
        System.out.println(Arrays.toString(arr));

    }


}
