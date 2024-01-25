package problem_helpers;

public class Ls1bIndexLookUpTable {
    private final short[] table;
    private final int TABLE_SIZE = 0xFFFF;

    public Ls1bIndexLookUpTable() {
        // Initialize the table with 2^16 entries
        table = new short[TABLE_SIZE];

        initializeTable();
    }

    private void initializeTable() {
        for (int i = 1; i < TABLE_SIZE; i++) {
            short index;
            if ((i & 1) == 0) {
                index = getLs1bIndex(i);
            } else {
                index = getLowestUnsetBit(i);
            }

            table[i] = index;
        }
    }

    // Getter
    public short getValue(int index) {
        // Retrieve a value from the table based on the provided index
        if (index > 0 && index < TABLE_SIZE) {
            return table[index];
        } else {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

    private static short getLs1bIndex(long x) {
        if (x == 0) {
            return 0;
        }

        short ls1bIndex = 0;

        while ((x & 1) == 0) {
            x >>= 1;
            ls1bIndex++;
        }

        return ls1bIndex;
    }

    private static short getLowestUnsetBit(long x) {
        short lubIndex = 0;

        while ((x & 1) == 1) {
            x >>= 1;
            lubIndex++;
        }

        return lubIndex;
    }
//    public static final short[] lookupTable =  new short[] {0, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 4, 4, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 5, 5, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 4, 4, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1, 1, 6, 6, 1, 1, 2, 2, 1, 1, 3, 3, 1, 1, 2, 2, 1};
}
