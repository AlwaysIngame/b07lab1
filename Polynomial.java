class Polynomial {
    double[] coefficients;

    Polynomial() {
        coefficients = new double[]{0.0};
    }

    Polynomial(double[] c) {
        coefficients = c;
    }

    Polynomial add(Polynomial o) {
        int i;
        double[] n = new double[Math.max(coefficients.length, o.coefficients.length)];

        for (i = 0; i < n.length; i++) n[i] = 0;

        for (i = 0; i < coefficients.length; i++) n[i] += coefficients[i];

        for (i = 0; i < o.coefficients.length; i++) n[i] += o.coefficients[i];

        return new Polynomial(n);
    }

    double evaluate(double x) {
        double sum = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, i);
        }
        return sum;
    }

    boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
}