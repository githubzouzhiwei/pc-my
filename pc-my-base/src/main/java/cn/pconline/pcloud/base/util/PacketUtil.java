package cn.pconline.pcloud.base.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 红包工具
 */
public class PacketUtil {

    /**
     * 获取随机金额（该方法需保证最大金额大于平均金额）
     *
     * @param totalAmount 总金额
     * @param packetNum   红包数
     * @param minAmount   最小金额
     * @param maxAmount   最大金额
     * @return
     */
    public static List<Integer> getRandomAmount(int totalAmount, int packetNum, int minAmount, int maxAmount) {
        int average = totalAmount / packetNum;// 平均金额

        List<Integer> packetAmounts = new ArrayList<>(packetNum);

        for (int i = 0; i < packetNum; i++) {
            int randomAmount;
            if (getRandomInt(minAmount, maxAmount) > average) {// 随机数大于平均数，分配小红包
                randomAmount = minAmount + getRandomDiffInt(minAmount, average);
            } else {// 随机数小于平均数，分配大红包
                randomAmount = maxAmount - getRandomDiffInt(average, maxAmount);
            }
            packetAmounts.add(randomAmount);
            totalAmount -= randomAmount;
        }

        // 总金额还有剩，平均分配到红包里
        while (totalAmount > 0) {
            for (int i = 0; i < packetAmounts.size(); i++) {
                if (totalAmount == 0) {// 分配完退出循环
                    break;
                }
                Integer integer = packetAmounts.get(i);
                if (integer == maxAmount) {// 已是最大金额，跳过
                    continue;
                }
                totalAmount--;
                integer++;
                packetAmounts.set(i, integer);
            }
        }
        // 总金额负数，从红包里扣
        while (totalAmount < 0) {
            for (int i = 0; i < packetAmounts.size(); i++) {
                if (totalAmount == 0) {// 总金额等于0无需再扣，退出循环
                    break;
                }
                Integer integer = packetAmounts.get(i);
                if (integer == minAmount) {// 已是最小金额，跳过
                    continue;
                }
                totalAmount++;
                integer--;
                packetAmounts.set(i, integer);
            }
        }

        return packetAmounts;
    }

    /**
     * 获取随机数
     *
     * @param minInt 最大值
     * @param maxInt 最小值
     * @return
     */
    private static int getRandomInt(int minInt, int maxInt) {
        return minInt + (int) (Math.random() * (maxInt - minInt + 1));
    }

    /**
     * 取最大和最小的差值的随机数
     * 先平方，得到随机数，再开方
     *
     * @param minInt 最大值
     * @param maxInt 最小值
     * @return
     */
    private static int getRandomDiffInt(int minInt, int maxInt) {
        double pow = Math.pow(maxInt - minInt, 2);
        int randomInt = getRandomInt(0, Double.valueOf(pow).intValue() - 1);
        return Double.valueOf(Math.sqrt(randomInt)).intValue();
    }

}
