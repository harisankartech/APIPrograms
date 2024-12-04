package GROUPATB;

public class BuilderPattern {


    //Make the return type of the method classname
    //Return this points to the same object
    //This always points to the same object
    //have same reference

    public BuilderPattern step1(){

        System.out.println("Hello step1");
        return this;
    }

    public BuilderPattern step2(){
        System.out.println("Hello step2");
        return this;
    }

    public BuilderPattern step3(String name){
        System.out.println("Hello step2" +name);
        return this;
    }

    public static void main(String[] args) {
        BuilderPattern bp = new BuilderPattern();
        bp.step1().step2().step3("Hari");
    }
}
