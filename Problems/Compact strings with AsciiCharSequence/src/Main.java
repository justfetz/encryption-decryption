import java.util.Arrays;

class AsciiCharSequence implements java.lang.CharSequence {

    private final byte[] b;

    public AsciiCharSequence(byte[] b) {
        this.b = b.clone();
    }


    @Override
    public int length() {
        return b.length;
    }

    @Override
    public char charAt(int index) {
        return (char) this.b[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {

        return new AsciiCharSequence(Arrays.copyOfRange(this.b, start, end));

        //byte[] b = this.b;

        //String str1 = new String(b);

        //return str1.subSequence(start, end);
    }

    public String toString() {
        byte[] byteArray = this.b;
        String str = new String(byteArray);
        return str;
    }
    // implementation
}
