import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
class Polynomial {
    double[] coefficients;
    int[] exponents;

    Polynomial() {
        coefficients = new double[]{0.0};
        exponents = new int[]{0};
    }

    Polynomial(double[] c, int[] e) {
        coefficients = c;
        exponents = e;
    }

    Polynomial(File f) throws Exception {
        Polynomial p = new Polynomial();
        Scanner s = new Scanner(f);
        s.useDelimiter("");
        while (s.hasNext()) {
            String t = s.next();
            System.out.println(s.hasNext("[+-]"));
            while (s.hasNext() && !s.hasNext("-") && !s.hasNext("\\+")) { t += s.next(); System.out.println(t);}
            String[] l = t.split("x");
            double[] c = new double[]{Double.parseDouble(l[0])};
            int[] e;
            if (l.length == 2) e = new int[]{Integer.parseInt(l[1])};
            else e = new int[]{0};
            p = p.add(new Polynomial(c,e));
        }
        coefficients = p.coefficients;
        exponents = p.exponents;
    }

    void simplify() {
        int l = 0;
        for (int i = 0; i < exponents.length; i++) {
            for (int j = i + 1; j < exponents.length; j++) {
                if (exponents[i] == exponents[j]) {
                    coefficients[j] += coefficients[i];
                    coefficients[i] = 0.0;
                    break;
                }
            }
            if (coefficients[i] != 0) l++;
        }

        if (l == 0) {
            coefficients = new double[]{0.0};
            exponents = new int[]{0};
        } else {
            double[] c = new double[l];
            int[] e = new int[l];
            for (int i = 0; i < coefficients.length; i++)
                if (coefficients[i] != 0) {
                    c[--l] = coefficients[i];
                    e[l] = exponents[i];
                }
            coefficients = c;
            exponents = e;
        }
    }

    Polynomial add(Polynomial o) {
        int i;
        double[] c = new double[coefficients.length + o.coefficients.length];
        int [] e = new int[exponents.length + o.exponents.length];


        for (i = 0; i < coefficients.length; i++) c[i] = coefficients[i];
        for (i = 0; i < o.coefficients.length; i++) c[coefficients.length + i] = o.coefficients[i];
        for (i = 0; i < exponents.length; i++) e[i] = exponents[i];
        for (i = 0; i < o.exponents.length; i++) e[i + exponents.length] = o.exponents[i];

        Polynomial p =  new Polynomial(c,e);
        p.simplify();
        return p;
    }

    Polynomial multiply(Polynomial o) {
        double[] c = new double[coefficients.length * o.coefficients.length];
        int[] e = new int[exponents.length * o.exponents.length];
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < o.coefficients.length; j++) {
                c[i*o.coefficients.length + j] = coefficients[i]*o.coefficients[j];
                e[i*o.exponents.length + j] = exponents[i] + o.exponents[j];
            }
        }
        Polynomial n = new Polynomial(c,e);
        n.simplify();
        return n;
    }

    double evaluate(double x) {
        double sum = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return sum;
    }

    boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
    
    void saveToFile(String s) throws Exception {
        File f = new File(s);
        f.createNewFile();
        FileWriter output = new FileWriter(f);

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] > 0 && i!=0) output.write("+");
            output.write(String.valueOf(coefficients[i]));
            if (exponents[i]!=0) output.write("x" + String.valueOf(exponents[i]));
        }
        output.close();
    }
}
