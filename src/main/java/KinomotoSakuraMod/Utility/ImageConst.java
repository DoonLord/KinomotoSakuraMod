package KinomotoSakuraMod.Utility;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class ImageConst
{
    //////////
    // 资源路径
    //////////
    // 卡牌背景
    public static final String ATTACK_BG_PATH = "img/cardui/clowcard/bg/attack.png";
    public static final String ATTACK_BG_LARGE_PATH = "img/cardui/clowcard/bg/attack_p.png";
    public static final String SKILL_BG_PATH = "img/cardui/clowcard/bg/skill.png";
    public static final String SKILL_BG_LARGE_PATH = "img/cardui/clowcard/bg/skill_p.png";
    public static final String POWER_BG_PATH = "img/cardui/clowcard/bg/power.png";
    public static final String POWER_BG_LARGE_PATH = "img/cardui/clowcard/bg/power_p.png";
    // 卡牌能量标识
    public static final String ORB_ATTACK_PATH = "img/cardui/clowcard/orb/orb_attack.png";
    public static final String ORB_ATTACK_LARGE_PATH = "img/cardui/clowcard/orb/orb_attack_p.png";
    public static final String ORB_SKILL_PATH = "img/cardui/clowcard/orb/orb_skill.png";
    public static final String ORB_SKILL_LARGE_PATH = "img/cardui/clowcard/orb/orb_skill_p.png";
    public static final String ORB_POWER_PATH = "img/cardui/clowcard/orb/orb_power.png";
    public static final String ORB_POWER_LARGE_PATH = "img/cardui/clowcard/orb/orb_power_p.png";
    // 卡牌稀有旗
    public static final String BANNER_COMMON_PATH = "img/cardui/clowcard/banner/common.png";
    public static final String BANNER_UNCOMMON_PATH = "img/cardui/clowcard/banner/uncommon.png";
    public static final String BANNER_RARE_PATH = "img/cardui/clowcard/banner/rare.png";
    // 卡牌稀有框
    public static final String FRAME_COMMON_PATH = "img/cardui/clowcard/frame/common.png";
    public static final String FRAME_UNCOMMON_PATH = "img/cardui/clowcard/frame/uncommon.png";
    public static final String FRAME_RARE_PATH = "img/cardui/clowcard/frame/rare.png";
    // 卡牌描述遮罩
    public static final String MASK_PATH = "img/cardui/clowcard/mask/mask.png";
    // 卡牌轮廓
    public static final String SILHOUETTE_PATH = "img/cardui/clowcard/silhouette/silhouette.png";
    // 卡牌闪光效果
    public static final String FLASH_PATH = "img/cardui/clowcard/flash/flash.png";
    // 角色图片素材路径
    public static final String SELECT_BUTTON_PATH = "img/charSelect/MarisaButton.png";
    public static final String PORTRAIT_PATH = "img/charSelect/marisaPortrait.jpg";

    //////////
    // 资源贴图
    //////////
    // 卡牌能量标识
    public static final Texture ORB_ATTACK;
    public static final Texture ORB_ATTACK_LARGE;
    public static final Texture ORB_SKILL;
    public static final Texture ORB_SKILL_LARGE;
    public static final Texture ORB_POWER;
    public static final Texture ORB_POWER_LARGE;
    // 卡牌稀有旗
    public static final Texture BANNER_COMMON;
    public static final Texture BANNER_UNCOMMON;
    public static final Texture BANNER_RARE;
    // 卡牌稀有框
    public static final Texture FRAME_COMMON;
    public static final Texture FRAME_UNCOMMON;
    public static final Texture FRAME_RARE;
    // 卡牌描述遮罩
    public static final Texture MASK;
    // 卡牌轮廓
    public static final Texture SILHOUETTE;
    // 卡牌闪光效果
    public static final Texture FLASH;

    static
    {
        ORB_ATTACK = ImageMaster.loadImage(ORB_ATTACK_PATH);
        ORB_ATTACK_LARGE = ImageMaster.loadImage(ORB_ATTACK_LARGE_PATH);
        ORB_SKILL = ImageMaster.loadImage(ORB_SKILL_PATH);
        ORB_SKILL_LARGE = ImageMaster.loadImage(ORB_SKILL_LARGE_PATH);
        ORB_POWER = ImageMaster.loadImage(ORB_POWER_PATH);
        ORB_POWER_LARGE = ImageMaster.loadImage(ORB_POWER_LARGE_PATH);
        BANNER_COMMON = ImageMaster.loadImage(BANNER_COMMON_PATH);
        BANNER_UNCOMMON = ImageMaster.loadImage(BANNER_UNCOMMON_PATH);
        BANNER_RARE = ImageMaster.loadImage(BANNER_RARE_PATH);
        FRAME_COMMON = ImageMaster.loadImage(FRAME_COMMON_PATH);
        FRAME_UNCOMMON = ImageMaster.loadImage(FRAME_UNCOMMON_PATH);
        FRAME_RARE = ImageMaster.loadImage(FRAME_RARE_PATH);
        MASK = ImageMaster.loadImage(MASK_PATH);
        SILHOUETTE = ImageMaster.loadImage(SILHOUETTE_PATH);
        FLASH = ImageMaster.loadImage(FLASH_PATH);
    }
}
