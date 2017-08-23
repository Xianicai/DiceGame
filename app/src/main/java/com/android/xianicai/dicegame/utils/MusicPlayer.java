package com.android.xianicai.dicegame.utils;

/**
 * Created by Zhanglibin on 2017/8/23.
 */

public class MusicPlayer {
//    private static MediaPlayer music;
//    private static SoundPool soundPool;
//
//    private static boolean musicSt = true; //音乐开关
//    private static boolean soundSt = true; //音效开关
//    private static Context context;
//
//    private static final int[] musicId = {R.raw.bg,R.raw.bg1,R.raw.bg2,R.raw.bg3};
//    private static Map<Integer,Integer> soundMap; //音效资源id与加载过后的音源id的映射关系表
//
//    /**
//     * 初始化方法
//     * @param c
//     */
//    public static void init(Context c)
//    {
//        context = c;
//
//        initMusic();
//
//        initSound();
//    }
//
//    //初始化音效播放器
//    private static void initSound()
//    {
//        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,100);
//
//        soundMap = new HashMap<Integer,Integer>();
//        soundMap.put(R.raw.itemboom, soundPool.load(context, R.raw.itemboom, 1));
//        soundMap.put(R.raw.sel, soundPool.load(context, R.raw.sel, 1));
//    }
//
//    //初始化音乐播放器
//    private static void initMusic()
//    {
//        int r = new Random().nextInt(musicId.length);
//        music = MediaPlayer.create(context,musicId[r]);
//        music.setLooping(true);
//    }
//
//    /**
//     * 播放音效
//     * @param resId 音效资源id
//     */
//    public static void playSound(int resId)
//    {
//        if(soundSt == false)
//            return;
//
//        Integer soundId = soundMap.get(resId);
//        if(soundId != null)
//            soundPool.play(soundId, 1, 1, 1, 0, 1);
//    }
//
//    /**
//     * 暂停音乐
//     */
//    public static void pauseMusic()
//    {
//        if(music.isPlaying())
//            music.pause();
//    }
//
//    /**
//     * 播放音乐
//     */
//    public static void startMusic()
//    {
//        if(musicSt)
//            music.start();
//    }
//
//    /**
//     * 切换一首音乐并播放
//     */
//    public static void changeAndPlayMusic()
//    {
//        if(music != null)
//            music.release();
//        initMusic();
//        startMusic();
//    }
//
//    /**
//     * 获得音乐开关状态
//     * @return
//     */
//    public static boolean isMusicSt() {
//        return musicSt;
//    }
//
//    /**
//     * 设置音乐开关
//     * @param musicSt
//     */
//    public static void setMusicSt(boolean musicSt) {
//        MusicPlayer.musicSt = musicSt;
//        if(musicSt)
//            music.start();
//        else
//            music.stop();
//    }
//
//    /**
//     * 获得音效开关状态
//     * @return
//     */
//    public static boolean isSoundSt() {
//        return soundSt;
//    }
//
//    /**
//     * 设置音效开关
//     * @param soundSt
//     */
//    public static void setSoundSt(boolean soundSt) {
//        MusicPlayer.soundSt = soundSt;
//    }
//
//    /**
//     * 发出‘邦’的声音
//     */
//    public static void boom()
//    {
//        playSound(R.raw.itemboom);
//    }
}
