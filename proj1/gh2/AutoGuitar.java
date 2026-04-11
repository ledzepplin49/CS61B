package gh2;

import edu.princeton.cs.algs4.StdAudio;

public class AutoGuitar {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int SR = 44100;

    public static void main(String[] args) {
        // 1. 初始化 37 根琴弦
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < KEYBOARD.length(); i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
        }

        // 2. 你的乐谱！(这里是一段示例旋律，你需要把它替换成 You Will See 的旋律)
        // 这里的字符就是你之前按的键盘按键，空格代表休止符(不弹)
        String sheetMusic =
                "cmmngngc" + // 小节 1
                        "cmmngngc" + // 小节 2
                        "cmmngngc" + // 小节 3
                        "cmmngngc" + // 小节 4
                        "cmmngngc" + // 小节 5
                        "cmmngngc" + // 小节 6
                        "ckmngngc";  // 小节 7 (带降E的高潮变调)

// 3. 节拍控制 (严格按照乐谱的 bpm=96，八分音符的准确时长)
        double beatDuration = 0.3125;


        System.out.println("开始自动演奏...");

        // 4. 自动播放循环
        for (int i = 0; i < sheetMusic.length(); i++) {
            char note = sheetMusic.charAt(i);
            int index = KEYBOARD.indexOf(note);

            // 如果不是休止符(在字典里能找到)，就拨动这根弦
            if (index != -1) {
                strings[index].pluck();
            }

            // 让时间流逝一段指定的节拍 (将秒数转换为需要的 tic 采样次数)
            int samplesToPlay = (int) (SR * beatDuration);
            for (int j = 0; j < samplesToPlay; j++) {
                double sample = 0.0;
                // 混音
                for (GuitarString s : strings) {
                    sample += s.sample();
                }
                // 发声
                StdAudio.play(sample);
                // 模拟衰减
                for (GuitarString s : strings) {
                    s.tic();
                }
            }
        }
        System.out.println("演奏结束！");
    }
}