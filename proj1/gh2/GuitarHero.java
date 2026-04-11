package gh2; // 确保包名和你的项目一致

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {

    // 老师提供的键盘映射字符串，共 37 个字符
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {

        // 1. 创建一个能够容纳 37 根吉他弦的数组
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];

        // 2. 初始化每一根弦，赋予它们不同的频率 (音高)
        for (int i = 0; i < KEYBOARD.length(); i++) {
            // 根据公式计算第 i 根弦的频率
            double frequency = 440.0 * Math.pow(2.0, (i - 24.0) / 12.0);
            // 实例化 GuitarString 对象并放进数组
            strings[i] = new GuitarString(frequency);
        }

        // 3. 进入游戏的主循环 (无限循环，直到关闭窗口)
        while (true) {

            // --- 阶段 A：检测用户的按键输入 ---
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                // 查找按下的键在 KEYBOARD 字符串里的位置 (索引)
                int index = KEYBOARD.indexOf(key);

                // 如果按下的键有效 (在字符串里能找到，即 index 不是 -1)
                if (index != -1) {
                    // 拨动对应位置的那根弦！
                    strings[index].pluck();
                }
            }

            // --- 阶段 B：混合声音 ---
            double sample = 0.0;
            // 把所有 37 根弦当前的声音数值累加起来（这就是和弦的原理！）
            for (int i = 0; i < strings.length; i++) {
                sample += strings[i].sample();
            }

            // --- 阶段 C：将混合后的声音发送给扬声器 ---
            StdAudio.play(sample);

            // --- 阶段 D：让所有弦的时间向前走一步 ---
            for (int i = 0; i < strings.length; i++) {
                strings[i].tic();
            }
        }
    }
}