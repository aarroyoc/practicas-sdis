package sdis.timeprinter;

public class TimePrinter implements Runnable {
    public void run(){
        while(true){
            System.out.println(new java.util.Date());
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                
            }
        }
    }

    public static void main(String[] args){
        final Runnable task = new TimePrinter();
        new Thread(task,"Hilo de TimePrinter").start();
    }
}