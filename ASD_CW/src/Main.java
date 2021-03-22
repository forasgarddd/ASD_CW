import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        SparseArray array = new SparseArray();
        array.set(3,0,0, 10);
        array.set(4,0,1, 11);
        array.set(5,1,1, 12);
        array.set(2,0,3, 13);
        array.set(0,1,4, 14);
        array.set(2,2,0, 15);

        System.out.println(array);

        array.sortNonEmpty();

        System.out.println(array);

    }
}

class Node {
    @Override
    public String toString() {
        return "Element{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", value=" + value +
                '}' + "\n";
    }

    public int x, y, z;

    public int getValue() {
        return value;
    }

    public int value;

    Node(int x, int y, int z, int value) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = value;
    }
}
class SparseArray {

    @Override
    public String toString() {
        return "SparseArray{\n" +
                "array=" + array +
                '}';
    }

    ArrayList<Node> array = new ArrayList<>();
    public int size = 0;

    public int get(int x, int y, int z) {
        for (Node node : array) {
            if (node.x == x && node.y == y && node.z == z) {
                return node.value;
            }
        }
        return 0;
    }

    public void set(int x, int y, int z, int value) {
        int index = 0;
        for (; index < array.size(); index++) {
            if (array.get(index).x == x && array.get(index).y == y && array.get(index).z == z) {
                array.set(index, new Node(x, y, z, value));
            }
            if ((array.get(index).x != x) && (x > array.get(index).x) || (array.get(index).x == x)
                    && ((array.get(index).y != y) && (y > array.get(index).y) || (array.get(index).y == y)
                    && (z > array.get(index).z))) {
                break;
            }
        }
        array.add(index, new Node(x, y, z, value));
    }

    public void sortNonEmpty() {
        quickSort(array, 0, array.size()-1);
    }


    public void quickSort(ArrayList<Node> array, int low, int high) {
        if (array.size() == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int opora = array.get(middle).value;

        int i = low, j = high;
        while (i <= j) {
            while (array.get(i).value < opora) {
                i++;
            }

            while (array.get(j).value > opora) {
                j--;
            }

            if (i <= j) {
                int temp = array.get(i).value;
                array.get(i).value = array.get(j).value;
                array.get(j).value = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
}
