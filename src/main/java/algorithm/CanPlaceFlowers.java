package algorithm;

public class CanPlaceFlowers {
    public static void main(String[] args) {
        CanPlaceFlowers f = new CanPlaceFlowers();

        System.out.println(f.canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
        System.out.println(f.canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
        System.out.println(f.canPlaceFlowers(new int[]{0,1,0,1,0}, 1));
        System.out.println(f.canPlaceFlowers(new int[]{1,0,0,1,0}, 1));
        System.out.println(f.canPlaceFlowers(new int[]{0}, 1));
        System.out.println(f.canPlaceFlowers(new int[]{1,0,0,0,0,0,0,1}, 1));
        System.out.println(f.canPlaceFlowers(new int[]{1,0,0,0,0,0,0,1}, 2));
        System.out.println(f.canPlaceFlowers(new int[]{1,0,0,0,0,0,0,1}, 3));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0)
            return true;

        int prev = -1;
        for (int i = 0; i < flowerbed.length; i++) {
            if (prev < 1 && flowerbed[i] < 1 && (flowerbed.length == i + 1 || flowerbed[i + 1] < 1)) {
                flowerbed[i] = 1;
                n--;
                if (n <= 0)
                    return true;
            }
            prev = flowerbed[i];
        }

        return n <= 0;
    }
}
