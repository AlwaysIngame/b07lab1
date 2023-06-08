import java.io.File;
public class Driver {
    public static void main(String [] args) throws Exception {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,3,4,5,-3};
        int [] e1 = {0,3,2,7, 4};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {1,-2,-2,8,-9};
        int [] e2 = {1,3,5,7,9};
        Polynomial p2 = new Polynomial(c2,e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(1) = " + s.evaluate(1));
        if(s.hasRoot(1)) System.out.println("1 is a root of s");
        else System.out.println("1 is not a root of s");

        s = p1.multiply(p2);
        s.saveToFile("s.p");
        System.out.println(s.evaluate(2.0));
        System.out.println(s.evaluate(2.0)== (p1.evaluate(2.0)*p2.evaluate(2.0)));

        Polynomial n = new Polynomial(new File("s.p"));
        n.saveToFile("s2.p");
    }
}
