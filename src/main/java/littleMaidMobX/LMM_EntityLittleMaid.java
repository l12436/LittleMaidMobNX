package littleMaidMobX;

import static littleMaidMobX.LMM_Statics.dataWatch_Absoption;
import static littleMaidMobX.LMM_Statics.dataWatch_Color;
import static littleMaidMobX.LMM_Statics.dataWatch_DominamtArm;
import static littleMaidMobX.LMM_Statics.dataWatch_ExpValue;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_Aimebow;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_Bloodsuck;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_Freedom;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_LooksSugar;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_OverDrive;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_Tracer;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_Wait;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_Working;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_looksWithInterest;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_looksWithInterestAXIS;
import static littleMaidMobX.LMM_Statics.dataWatch_Flags_remainsContract;
import static littleMaidMobX.LMM_Statics.dataWatch_Free;
import static littleMaidMobX.LMM_Statics.dataWatch_Gotcha;
import static littleMaidMobX.LMM_Statics.dataWatch_ItemUse;
import static littleMaidMobX.LMM_Statics.dataWatch_Mode;
import static littleMaidMobX.LMM_Statics.dataWatch_Parts;
import static littleMaidMobX.LMM_Statics.dataWatch_Texture;
import mmmlibx.lib.ITextureEntity;
import mmmlibx.lib.MMMLib;
import mmmlibx.lib.MMM_Counter;
import mmmlibx.lib.MMM_Helper;
import mmmlibx.lib.MMM_TextureBox;
import mmmlibx.lib.MMM_TextureBoxBase;
import mmmlibx.lib.MMM_TextureData;
import mmmlibx.lib.MMM_TextureManager;
import mmmlibx.lib.multiModel.model.mc162.EquippedStabilizer;
import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
import net.blacklab.lib.ItemUtil;
import net.blacklab.lmmnx.LMMNX_Achievements;
import net.blacklab.lmmnx.LMMNX_EntityAIOpenDoor;
import net.blacklab.lmmnx.LMMNX_EntityAIRestrictOpenDoor;
import net.blacklab.lmmnx.LMMNX_ItemRegisterKey;
import net.blacklab.lmmnx.LMMNX_NetSync;
import net.blacklab.lmmnx.api.item.LMMNX_API_Item;
import net.blacklab.lmmnx.api.item.LMMNX_IItemSpecialSugar;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSkull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import net.minecraft.world.pathfinder.WalkNodeProcessor;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import wrapper.W_Common;

public class LMM_EntityLittleMaid extends EntityTameable implements ITextureEntity {

	// 定数はStaticsへ移動
//	protected static final UUID maidUUID = UUID.nameUUIDFromBytes("lmm.littleMaidMob".getBytes());
	protected static final UUID maidUUID = UUID.fromString("e2361272-644a-3028-8416-8536667f0efb");
//	protected static final UUID maidUUIDSneak = UUID.nameUUIDFromBytes("lmm.littleMaidMob.sneak".getBytes());
	protected static final UUID maidUUIDSneak = UUID.fromString("5649cf91-29bb-3a0c-8c31-b170a1045560");
	protected static AttributeModifier attCombatSpeed = (new AttributeModifier(maidUUID, "Combat speed boost", 0.07D, 0)).setSaved(false);
	protected static AttributeModifier attAxeAmp = (new AttributeModifier(maidUUID, "Axe Attack boost", 0.5D, 1)).setSaved(false);
	protected static AttributeModifier attSneakingSpeed = (new AttributeModifier(maidUUIDSneak, "Sneking speed ampd", -0.4D, 2)).setSaved(false);


//	protected long maidContractLimit;		// 契約失効日
	protected int maidContractLimit;		// 契約期間
	public long maidAnniversary;			// 契約日UIDとして使用
	protected int maidDominantArm;			// 利き腕、1Byte
	/** テクスチャ関連のデータを管理 **/
	public MMM_TextureData textureData;
	public Map<String, EquippedStabilizer> maidStabilizer = new HashMap<String, EquippedStabilizer>();

	public float getLastDamage(){
		return lastDamage;
	}

	public int fencerDefDetonateTick = 0;

//	public int jumpTicks;

	public LMM_InventoryLittleMaid maidInventory;
	public EntityPlayer maidAvatar;
	public LMM_EntityCaps maidCaps;	// Client側のみ

	public List<LMM_EntityModeBase> maidEntityModeList;
	public Map<Integer, EntityAITasks[]> maidModeList;
	public Map<String, Integer> maidModeIndexList;
	public int maidMode;		// 2Byte
	public boolean maidTracer;
	public boolean maidFreedom;
	public boolean maidWait;
	public int homeWorld;
	protected int maidTiles[][] = new int[9][3];
	public int maidTile[] = new int[3];
	public TileEntity maidTileEntity;

	// 動的な状態
	protected EntityPlayer mstatMasterEntity;	// 主
	protected double mstatMasterDistanceSq;		// 主との距離、計算軽量化用
	protected Entity mstatgotcha;				// ワイヤード用
	protected boolean mstatBloodsuck;
	protected boolean mstatClockMaid;
	// マスク判定
	protected int mstatMaskSelect;
	// 追加の頭部装備
	protected boolean mstatCamouflage;
	protected boolean mstatPlanter;
//	protected boolean isMaidChaseWait;
	protected int mstatWaitCount;
	protected int mstatTime;
	protected MMM_Counter maidOverDriveTime;
	protected boolean mstatFirstLook;
	protected boolean mstatLookSuger;
	protected MMM_Counter mstatWorkingCount;
	protected int mstatPlayingRole;
	protected int mstatWorkingInt;
	protected String mstatModeName;
	protected boolean mstatOpenInventory;
	// 腕振り
	public LMM_SwingStatus mstatSwingStatus[];
	public boolean mstatAimeBow;
	// 首周り
	private boolean looksWithInterest;
	private boolean looksWithInterestAXIS;
	private float rotateAngleHead;			// Angle
	private float prevRotateAngleHead;		// prevAngle

	public float defaultWidth;
	public float defaultHeight;

	/**
	 * 個体ごとに値をバラつかせるのに使う。
	 */
	public float entityIdFactor;

	public boolean weaponFullAuto;	// 装備がフルオート武器かどうか
	public boolean weaponReload;	// 装備がリロードを欲しているかどうか
	public boolean maidCamouflage;

	// 音声
//	protected LMM_EnumSound maidAttackSound;
	protected LMM_EnumSound maidDamegeSound;
	protected int maidSoundInterval;
	protected float maidSoundRate;

	// クライアント専用音声再生フラグ
	private ArrayList<LMM_EnumSound> playingSound = new ArrayList<LMM_EnumSound>();

	// 実験用
	private int firstload = 1;
	public String statusMessage = "";

	// AI
	public EntityAITempt aiTempt;
	public LMM_EntityAIBeg aiBeg;
	public LMM_EntityAIBegMove aiBegMove;
	public LMMNX_EntityAIOpenDoor aiOpenDoor;
	public LMMNX_EntityAIRestrictOpenDoor aiCloseDoor;
	public LMM_EntityAIAvoidPlayer aiAvoidPlayer;
	public LMM_EntityAIFollowOwner aiFollow;
	public LMM_EntityAIAttackOnCollide aiAttack;
	public LMM_EntityAIAttackArrow aiShooting;
	public LMM_EntityAICollectItem aiCollectItem;
	public LMM_EntityAIRestrictRain aiRestrictRain;
	public LMM_EntityAIFleeRain aiFreeRain;
	public LMM_EntityAIWander aiWander;
	public LMM_EntityAIJumpToMaster aiJumpTo;
	public LMM_EntityAIFindBlock aiFindBlock;
	public LMM_EntityAITracerMove aiTracer;
	public LMM_EntityAISwimming aiSwiming;
	public EntityAIPanic aiPanic;

	// ActiveModeClass
	protected LMM_EntityModeBase maidActiveModeClass;
	public Profiler aiProfiler;

	//モデル
	public String textureModelNameForClient;
	public String textureArmorNameForClient;

	public int playingTick = 0;

	protected boolean isWildSaved = false;

	public boolean swimmingEnabled = false;

	// サーバ用テクスチャ処理移行フラグ
	private boolean isMadeTextureNameFlag = false;

	protected int maidArmorVisible = 15;

	protected int registerTick = -1;
	protected String registerMode;

	public LMM_EntityLittleMaid(World par1World) {
		super(par1World);
		// 初期設定
		maidInventory = new LMM_InventoryLittleMaid(this);
		if (par1World != null ) {
			if(par1World.isRemote)
			{
				maidAvatar = new LMM_EntityLittleMaidAvatar(par1World, this);
			}
			else
			{
				try{
					maidAvatar = new LMM_EntityLittleMaidAvatarMP(par1World, this);
				}catch(Throwable throwable){
					maidAvatar = null;
					setDead();
					return;
				}
			}
		}
		mstatOpenInventory = false;
//		isMaidChaseWait = false;
		mstatTime = 6000;
		maidOverDriveTime = new MMM_Counter(5, 300, -LMM_LittleMaidMobNX.cfg_maidOverdriveDelay);
		mstatWorkingCount = new MMM_Counter(11, 10, -10);

		// モデルレンダリング用のフラグ獲得用ヘルパー関数
		maidCaps = new LMM_EntityCaps(this);

		// 形態形成場
		textureData = new MMM_TextureData(this, maidCaps);
		textureData.setColor(12);
		MMM_TextureBox ltb[] = new MMM_TextureBox[2];
		ltb[0] = ltb[1] = MMM_TextureManager.instance.getDefaultTexture(this);
		setTexturePackName(ltb);

		entityIdFactor = getEntityId() * 70;
		// 腕振り
		mstatSwingStatus = new LMM_SwingStatus[] { new LMM_SwingStatus(), new LMM_SwingStatus()};
		setDominantArm(rand.nextInt(mstatSwingStatus.length));

		// 再生音声
//		maidAttackSound = LMM_EnumSound.attack;
		maidDamegeSound = LMM_EnumSound.hurt;
		maidSoundInterval = 0;

		//this.dataWatcher.addObject(16, new Byte((byte)0));

		// 野生種用初期値設定
		setHealth(15F);

		//1.8検討
		// 移動用フィジカル設定
		//getNavigator().setAvoidsWater(true);
		//getNavigator().setBreakDoors(true);

		// TODO:これはテスト
//		maidStabilizer.put("HeadTop", MMM_StabilizerManager.getStabilizer("WitchHat", "HeadTop"));

		// EntityModeの追加
		maidEntityModeList = LMM_EntityModeManager.getModeList(this);
		// モードリスト
		maidActiveModeClass = null;
		maidModeList = new HashMap<Integer, EntityAITasks[]>();
		maidModeIndexList = new HashMap<String, Integer>();
		initModeList();
		mstatModeName = "";
		maidMode = 65535;
		// 初期化時実行コード
		for (LMM_EntityModeBase lem : maidEntityModeList) {
			lem.initEntity();
		}

		/*
		if(par1World.isRemote){
			NBTTagCompound t = new NBTTagCompound();
			writeEntityToNBT(t);
			readEntityFromNBT(t);
			t = null;
		}
		*/
	}

	public LMM_IEntityLittleMaidAvatar getAvatarIF()
	{
		return (LMM_IEntityLittleMaidAvatar)maidAvatar;
	}

	public void onSpawnWithEgg() {
		// テクスチャーをランダムで選択
		String ls;
		if (LMM_LittleMaidMobNX.cfg_isFixedWildMaid) {
			ls = "default_Orign";
		} else {
			ls = MMM_TextureManager.instance.getRandomTextureString(rand);
		}
		textureData.setTextureInitServer(ls);
		LMM_LittleMaidMobNX.Debug("init-ID:%d, %s:%d", getEntityId(), textureData.textureBox[0].textureName, textureData.getColor());
		setTexturePackIndex(textureData.getColor(), textureData.textureIndex);
		textureModelNameForClient = textureData.textureBox[0].textureName;
		textureArmorNameForClient = textureData.textureBox[1].textureName;
		recallRenderParamTextureName(textureModelNameForClient, textureArmorNameForClient);
		if(!isContract()) setMaidMode("Wild");
	}

	@Override
	protected void applyEntityAttributes() {
		// 初期パラメーター
		super.applyEntityAttributes();
		// 対象移動可能範囲
		getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0D);
		// 基本移動速度
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
		// 標準攻撃力１
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(1.0D);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		/*
		 * DataWatcherはクライアントからサーバーへは値を渡さない、渡せない。
		 */

		// 使用中リスト
		// 0:Flags
		// 1:Air
		// 2, 3, 4, 5,
		// 6: HP
		// 7, 8:PotionMap
		// 9: ArrowCount
		// 10: 固有名称
		// 11: 名付判定
		// 12: GrowingAge
		// 16: Tame(4), Sit(1)
		// 17: ownerName

		// maidAvater用EntityPlayer互換変数
		// 17 -> 18
		// 18 : Absoption効果をクライアント側へ転送するのに使う
		dataWatcher.addObject(dataWatch_Absoption, Float.valueOf(0.0F));

		// 独自分
		// 19:maidColor
		dataWatcher.addObject(dataWatch_Color, Byte.valueOf((byte)0));
		// 20:選択テクスチャインデックス
		dataWatcher.addObject(dataWatch_Texture, Integer.valueOf(0));
		// 21:モデルパーツの表示フラグ
		dataWatcher.addObject(dataWatch_Parts, Integer.valueOf(0));
		// 22:状態遷移フラグ群(32Bit)、詳細はStatics参照
		dataWatcher.addObject(dataWatch_Flags, Integer.valueOf(0));
		// 23:GotchaID
		dataWatcher.addObject(dataWatch_Gotcha, Integer.valueOf(0));
		// 24:メイドモード
		dataWatcher.addObject(dataWatch_Mode, Short.valueOf((short)0));
		// 25:利き腕
		dataWatcher.addObject(dataWatch_DominamtArm, Byte.valueOf((byte)0));
		// 26:アイテムの使用判定
		dataWatcher.addObject(dataWatch_ItemUse, Integer.valueOf(0));
		// 27:保持経験値
		dataWatcher.addObject(dataWatch_ExpValue, Integer.valueOf(0));

		// TODO:test
		// 31:自由変数、EntityMode等で使用可能な変数。
		dataWatcher.addObject(dataWatch_Free, new Integer(0));

		defaultWidth = width;
		defaultHeight = height;
	}

	public void initModeList() {
		// AI
		aiBeg = new LMM_EntityAIBeg(this, 8F);
		aiBegMove = new LMM_EntityAIBegMove(this, 1.0F);
		aiOpenDoor = new LMMNX_EntityAIOpenDoor(this, true);
		aiCloseDoor = new LMMNX_EntityAIRestrictOpenDoor(this);
		aiAvoidPlayer = new LMM_EntityAIAvoidPlayer(this, 1.0F, 3);
		aiFollow = new LMM_EntityAIFollowOwner(this, 1.0F, 36D, 25D, 81D);
		aiAttack = new LMM_EntityAIAttackOnCollide(this, 1.0F, true);
		aiShooting = new LMM_EntityAIAttackArrow(this);
		aiCollectItem = new LMM_EntityAICollectItem(this, 1.0F);
		aiRestrictRain = new LMM_EntityAIRestrictRain(this);
		aiFreeRain = new LMM_EntityAIFleeRain(this, 1.0F);
		aiWander = new LMM_EntityAIWander(this, 1.0F);
		aiJumpTo = new LMM_EntityAIJumpToMaster(this);
		aiFindBlock = new LMM_EntityAIFindBlock(this);
		aiSwiming = new LMM_EntityAISwimming(this);
		aiPanic = new EntityAIPanic(this, 2.0F);
		aiTracer = new LMM_EntityAITracerMove(this);
		aiSit = new LMM_EntityAIWait(this);

		// TODO:これいらなくね？
		aiProfiler = worldObj != null && worldObj.theProfiler != null ? worldObj.theProfiler : null;

		// 動作モード用のTasksListを初期化
		EntityAITasks ltasks[] = new EntityAITasks[2];
		ltasks[0] = new EntityAITasks(aiProfiler);
		ltasks[1] = new EntityAITasks(aiProfiler);

		// default
		ltasks[0].addTask(1, aiSwiming);
		ltasks[0].addTask(2, aiSit);
		ltasks[0].addTask(3, aiJumpTo);
		ltasks[0].addTask(4, aiFindBlock);
		ltasks[0].addTask(5, aiAttack);
		ltasks[0].addTask(6, aiShooting);
		//ltasks[0].addTask(8, aiPanic);
		ltasks[0].addTask(10, aiBeg);
		ltasks[0].addTask(11, aiBegMove);
		ltasks[0].addTask(20, aiAvoidPlayer);
		ltasks[0].addTask(21, aiFreeRain);
		ltasks[0].addTask(22, aiCollectItem);
		// 移動用AI
		ltasks[0].addTask(30, aiTracer);
		ltasks[0].addTask(31, aiFollow);
		ltasks[0].addTask(32, aiWander);
		ltasks[0].addTask(33, new EntityAILeapAtTarget(this, 0.3F));
		// Mutexの影響しない特殊行動
		ltasks[0].addTask(40, aiCloseDoor);
		ltasks[0].addTask(41, aiOpenDoor);
		ltasks[0].addTask(42, aiRestrictRain);
		// 首の動き単独
		ltasks[0].addTask(51, new EntityAIWatchClosest(this, EntityLivingBase.class, 10F));
		ltasks[0].addTask(52, new EntityAILookIdle(this));

		// 追加分
		for (LMM_EntityModeBase ieml : maidEntityModeList) {
			ieml.addEntityMode(ltasks[0], ltasks[1]);
		}
	}


	public void addMaidMode(EntityAITasks[] peaiTasks, String pmodeName, int pmodeIndex) {
		maidModeList.put(pmodeIndex, peaiTasks);
		maidModeIndexList.put(pmodeName, pmodeIndex);
	}


	public int getMaidModeInt() {
		return maidMode;
	}

	public String getMaidModeString() {
		if (!isContract()) {
			return getMaidModeString(maidMode);
		} else if (!isRemainsContract()) {
			return "Strike";
		} else if (isMaidWait()) {
			return "Wait";
		} else if (isPlaying()) {
			return "Playing";
		} else {
			String ls = getMaidModeString(maidMode);
//			if (maidOverDriveTime.isEnable()) {
//				ls = "D-" + ls;
//			} else
			if (isTracer()&&isFreedom()) {
				ls = "T-" + ls;
			} else if (isFreedom()) {
				ls = "F-" + ls;
			}
			return ls;
		}
	}

	public String getMaidModeString(int pindex) {
		// モード名称の獲得
		String ls = "";
		for (Entry<String, Integer> le : maidModeIndexList.entrySet()) {
			if (le.getValue() == pindex) {
				ls = le.getKey();
				break;
			}
		}
		return ls;
	}

	public boolean setMaidMode(String pname) {
		return setMaidMode(pname, false);
	}

	public boolean setMaidMode(String pname, boolean pplaying) {
		if (!maidModeIndexList.containsKey(pname)) {
			return false;
		}
		return setMaidMode(maidModeIndexList.get(pname), pplaying);
	}

	public boolean setMaidMode(int pindex) {
		return setMaidMode(pindex, false);
	}

	public void setSwimming(boolean flag){
		swimmingEnabled = flag;
	}

	public void setMaidArmorVisible(int i){
		if(i<0) i=0;
		if(i>15)i=15;
		maidArmorVisible = i;
	}

	public void setMaidArmorVisible(boolean a, boolean b, boolean c, boolean d){
		setMaidArmorVisible((a?1:0)<<3 | (b?1:0)<<2 | (c?1:0)<<1 | (d?1:0));
	}

	public boolean isArmorVisible(int index){
		if(index<0||index>3) return true;
		return ((maidArmorVisible>>(3-index)) & 0x1) != 0;
	}

	protected void syncSwimming(){
		byte[] b = new byte[]{
				LMMNX_NetSync.LMMNX_Sync,
				0, 0, 0, 0,
				LMMNX_NetSync.LMMNX_Sync_UB_Swim, (byte)(swimmingEnabled?1:0)
		};
		syncNet(b);
	}

	public void syncMaidArmorVisible() {
		byte[] b = new byte[]{
				LMMNX_NetSync.LMMNX_Sync,
				0, 0, 0, 0,
				LMMNX_NetSync.LMMNX_Sync_UB_Armor, (byte)maidArmorVisible
		};
		syncNet(b);
	}

	/**
	 * Client用。
	 * ログイン時や描画更新時にパラメータの更新をリクエスト
	 */
	protected void requestRenderParamRecall(){
		if(FMLCommonHandler.instance().getSide()!=Side.CLIENT) return;
		byte[] b = new byte[]{
				LMMNX_NetSync.LMMNX_Sync,
				0, 0, 0, 0,
				LMMNX_NetSync.LMMNX_Sync_UB_RequestParamRecall, 0
		};
		syncNet(b);
	}

	/**
	 * Server用。
	 * ログイン時・変更時のリクエストに応答
	 */
	public void recallRenderParamTextureName(String model, String armor){
		LMM_LittleMaidMobNX.Debug("RECALL %s %s", model, armor);
//		if(model==null||armor==null) return;
		textureModelNameForClient = model;
		textureArmorNameForClient = armor;
		if(!isMadeTextureNameFlag){
			LMM_LittleMaidMobNX.Debug("NOT SAVED!");
			String p1 = textureData.textureBox[0].textureName;
			String p2 = textureData.textureBox[1].textureName;
			if(p1==null || p2==null) return;
			textureModelNameForClient = p1;
			textureArmorNameForClient = p2;
			isMadeTextureNameFlag = true;
		}
		syncMAString(textureModelNameForClient, textureArmorNameForClient, false);
	}

	private boolean isNotColorExistsWild = false;

	/**
	 * Client用。
	 * Serverからの通知を受け取りパラメータを再設定
	 */
	public void returnedRecallParam(String model, String armor){
		textureModelNameForClient = model;
		textureArmorNameForClient = armor;

		MMM_TextureBox p0 = referTextureBox(model);
		MMM_TextureBox p1 = referTextureArmorBox(armor);
		MMM_TextureBox pBox[] = new MMM_TextureBox[]{p0, p1};
		pBox[0]= pBox[0]==null ? referTextureBox("default_Orign") : pBox[0];
		pBox[1]= pBox[1]==null ? referTextureArmorBox("default_Orign") : pBox[1];

		// 指定色がない場合
		if(!isContract() && (pBox[0].getWildColorBits() & 1<<getColor()) == 0){
			isNotColorExistsWild = true;
		}
		setTexturePackName(pBox);
		if(isNotColorExistsWild){
			textureData.setColor(12);
		}
		setTextureNames();
	}

	/**
	 * Client用。
	 * モデルパラメータの変更時にパラメータの更新をリクエスト
	 */
	public void requestChangeRenderParamTextureName(){
		String p1 = textureData.textureBox[0].textureName;
		String p2 = textureData.textureBox[1].textureName;
		if(!isMadeTextureNameFlag) isMadeTextureNameFlag = true;
		syncMAString(p1, p2, true);
	}

	protected void syncMAString(String model, String armor, boolean isServerSave) {
		// Model側
		if(model==null){
			return;
		}
		byte b1[] = new byte[]{
				LMMNX_NetSync.LMMNX_Sync,
				0, 0, 0, 0,
				isServerSave?LMMNX_NetSync.LMMNX_Sync_String_MT_RequestChangeRender:LMMNX_NetSync.LMMNX_Sync_String_MT_RecallParam
		};
		byte b1b[] = Arrays.copyOf(b1, b1.length+model.length());
		MMM_Helper.setStr(b1b, 6, model);
		syncNet(b1b);

		// Armor側
		if(armor==null){
			return;
		}
		byte b2[] = new byte[]{
				LMMNX_NetSync.LMMNX_Sync,
				0, 0, 0, 0,
				isServerSave?LMMNX_NetSync.LMMNX_Sync_String_AT_RequestChangeRender:LMMNX_NetSync.LMMNX_Sync_String_AT_RecallParam
		};
		byte b2b[] = Arrays.copyOf(b2, b2.length+armor.length());
		MMM_Helper.setStr(b2b, 6, armor);
		syncNet(b2b);
	}

	/**
	 * Client専用。
	 */
	public static MMM_TextureBox referTextureBox(String string) {
		for (Iterator iterator = MMM_TextureManager.getTextureList().iterator(); iterator.hasNext();) {
			MMM_TextureBox lBox = (MMM_TextureBox) iterator.next();
			if(lBox.textureName.equals(string)) return lBox;
		}
		return null;
	}

	/**
	 * Client専用。
	 */
	public static MMM_TextureBox referTextureArmorBox(String string){
		for (Iterator iterator = MMM_TextureManager.getTextureList().iterator(); iterator.hasNext();) {
			MMM_TextureBox lBox = (MMM_TextureBox) iterator.next();
			if(lBox.hasArmor()&&lBox.textureName.equals(string)) return lBox;
		}
		return null;
	}


	protected void syncNet(byte[] b) {
		if(worldObj.isRemote){
			LMM_Net.sendToEServer(this, b);
		}else{
			LMM_Net.sendToAllEClient(this, b);
		}
	}

	public boolean setMaidMode(int pindex, boolean pplaying) {
		// モードに応じてAIを切り替える
		velocityChanged = true;
		if (!maidModeList.containsKey(pindex)) return false;
		if (maidMode == pindex) return true;

		if (pplaying) {

		} else {
			mstatWorkingInt = pindex;
		}
		mstatModeName = getMaidModeString(pindex);
		maidMode = pindex;
		dataWatcher.updateObject(dataWatch_Mode, (short)maidMode);
		EntityAITasks[] ltasks = maidModeList.get(pindex);

		// AIを根底から書き換える
		if (ltasks.length > 0 && ltasks[0] != null) {
			setMaidModeAITasks(ltasks[0], tasks);
		} else {
			setMaidModeAITasks(null, tasks);
		}
		if (ltasks.length > 1 && ltasks[1] != null) {
			setMaidModeAITasks(ltasks[1], targetTasks);
		} else {
			setMaidModeAITasks(null, targetTasks);
		}

		// モード切替に応じた処理系を確保
		maidAvatar.stopUsingItem();
		setSitting(false);
		setSneaking(false);
		setActiveModeClass(null);
		aiJumpTo.setEnable(true);
//		aiFollow.setEnable(true);
		aiAttack.setEnable(true);
		aiShooting.setEnable(false);
		aiAvoidPlayer.setEnable(true);
//		aiWander.setEnable(maidFreedom);
		setBloodsuck(false);
		clearTilePosAll();
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			LMM_EntityModeBase iem = maidEntityModeList.get(li);
			if (iem.setMode(maidMode)) {
				setActiveModeClass(iem);
				aiFollow.minDist = iem.getRangeToMaster(0);
				aiFollow.maxDist = iem.getRangeToMaster(1);
				break;
			}
		}
		getNextEquipItem();

		return true;
	}

	protected void setMaidModeAITasks(EntityAITasks pTasksSRC, EntityAITasks pTasksDEST) {
		// 既存のAIを削除して置き換える。
		// 動作をクリア
		try {
			ArrayList<EntityAITaskEntry> ltasksDoDEST = getEntityAITasks_taskEntries(pTasksDEST);
			ArrayList<EntityAITaskEntry> ltasksExeDEST = getEntityAITasks_executingTaskEntries(pTasksDEST);

			if (pTasksSRC == null) {
				ltasksDoDEST.clear();
				ltasksExeDEST.clear();
			} else {
				ArrayList<EntityAITaskEntry> ltasksDoSRC = getEntityAITasks_taskEntries(pTasksSRC);
				getEntityAITasks_executingTaskEntries(pTasksSRC);

				Iterator iterator;
				iterator = ltasksExeDEST.iterator();
				while (iterator.hasNext()) {
					EntityAITaskEntry ltaskentory = (EntityAITaskEntry)iterator.next();
					ltaskentory.action.resetTask();
				}
				ltasksExeDEST.clear();

				ltasksDoDEST.clear();
				ltasksDoDEST.addAll(ltasksDoSRC);
				// TODO: 未実装の機能、モードチェンジ時の初期化を行う。
//				for (EntityAITaskEntry ltask : ltasksDoSRC) {
//					if (ltask instanceof LMM_IEntityAI)
//					{
//						((LMM_IEntityAI)ltask).setDefaultEnable();
//					}
//				}
			}
		} catch (Exception s) {
		}
	}
	public static ArrayList<EntityAITaskEntry> getEntityAITasks_taskEntries(EntityAITasks task)
	{
		return (ArrayList<EntityAITaskEntry>) task.taskEntries;
	}
	public static ArrayList<EntityAITaskEntry> getEntityAITasks_executingTaskEntries(EntityAITasks task)
	{
		return (ArrayList<EntityAITaskEntry>) task.taskEntries;
	}

	/**
	 * 適用されているモードクラス
	 */
	public LMM_EntityModeBase getActiveModeClass() {
		return maidActiveModeClass;
	}

	public void setActiveModeClass(LMM_EntityModeBase pEntityMode) {
		maidActiveModeClass = pEntityMode;
	}

	public boolean isActiveModeClass() {
		return maidActiveModeClass != null;
	}

	// 効果音の設定
	@Override
	protected String getHurtSound() {
		if(getHealth()>0f) playLittleMaidSound(maidDamegeSound, true);
		return null;
	}

	@Override
	protected String getDeathSound() {
		playLittleMaidSound(LMM_EnumSound.death, true);
		return null;
	}

	@Override
	protected String getLivingSound() {
		// 普段の声
		//LMM_LittleMaidMobNX.Debug("DEBUG INFO=tick %d", livingSoundTick);
		//livingSoundTick--;
		return "dummy.living";
	}


	/**
	 * 簡易音声再生、標準の音声のみ使用すること。
	 */
	public void playSound(String pname) {
		if(!worldObj.isRemote) playSound(pname, 0.5F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
	}

	/**
	 * ネットワーク対応音声再生
	 */
	public void playSound(LMM_EnumSound enumsound, boolean force) {
		if (enumsound == LMM_EnumSound.Null) return;
		if (worldObj.isRemote && enumsound!=LMM_EnumSound.Null) {
			// NX1B47:サウンド乱数制限をクライアント依存に
			if(!force||LMM_LittleMaidMobNX.cfg_ignoreForceSound){
				if(LMM_LittleMaidMobNX.cfg_soundPlayChance!=1 &&
						LMM_LittleMaidMobNX.randomSoundChance.nextInt(LMM_LittleMaidMobNX.cfg_soundPlayChance)!=0) return;
			}

			if(force){
				playingSound.add(0, enumsound);
			}else{
				playingSound.add(enumsound);
			}
//			float lpitch = LMM_LittleMaidMobNX.cfg_VoiceDistortion ? (rand.nextFloat() * 0.2F) + 0.95F : 1.0F;
//			LMM_LittleMaidMobNX.proxy.playLittleMaidSound(worldObj, posX, posY, posZ, s, getSoundVolume(), lpitch, false);
		}
	}

	/**
	 * 音声再生用。
	 * 通常の再生ではネットワーク越しになるのでその対策。
	 */
	public void playLittleMaidSound(LMM_EnumSound enumsound, boolean force) {
		// 音声の再生
		if (enumsound == LMM_EnumSound.Null) return;
		maidSoundInterval = 20;
		if (!worldObj.isRemote) {
			// Server
//			if((LMM_LittleMaidMobNX.cfg_ignoreForceSound || !force) && new Random().nextInt(LMM_LittleMaidMobNX.cfg_soundPlayChance)!=0) return;
			LMM_LittleMaidMobNX.Debug("id:%d-%s, seps:%04x-%s", getEntityId(), "Server",  enumsound.index, enumsound.name());
			byte[] lbuf = new byte[] {
					LMM_Statics.LMN_Client_PlaySound,
					0, 0, 0, 0,
					0, 0, 0, 0,
					0, 0, 0, 0
			};
			MMM_Helper.setInt(lbuf, 5, enumsound.index);
			MMM_Helper.setInt(lbuf, 9, force?1:0);
			LMM_Net.sendToAllEClient(this, lbuf);
		}
	}

	@Override
	public void playLivingSound() {
		// 普段の声
		//LMM_LittleMaidMobNX.Debug("DEBUG INFO=tick %d", livingSoundTick);
		//livingSoundTick--;
		if(getAttackTarget()!=null) return;
		LMM_EnumSound so = LMM_EnumSound.Null;
		if (getHealth() < 10)
			so = LMM_EnumSound.living_whine;
		else if (rand.nextFloat() < maidSoundRate) {
			if (mstatTime > 23500 || mstatTime < 1500) {
				so = LMM_EnumSound.living_morning;
			} else if (mstatTime < 12500) {
				if (isContract()) {
					BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords(new BlockPos(MathHelper.floor_double(posX + 0.5D), posY, MathHelper.floor_double(posZ + 0.5D)));
					TempCategory ltemp = biomegenbase.getTempCategory();
					if (ltemp == TempCategory.COLD) {
						so = LMM_EnumSound.living_cold;
					} else if (ltemp == TempCategory.WARM) {
						so = LMM_EnumSound.living_hot;
					} else {
						so = LMM_EnumSound.living_daytime;
					}
					if (worldObj.isRaining()) {
						if (biomegenbase.canSpawnLightningBolt()) {
							so = LMM_EnumSound.living_rain;
						} else if (biomegenbase.getEnableSnow()) {
							so = LMM_EnumSound.living_snow;
						}
					}
				} else {
					so = LMM_EnumSound.living_daytime;
				}
			} else {
				so = LMM_EnumSound.living_night;
			}
		}

		//if(livingSoundTick<=0){
			LMM_LittleMaidMobNX.Debug("id:%d LivingSound:%s", getEntityId(), worldObj == null ? "null" : worldObj.isRemote ? "Client" : "Server");
			if(!worldObj.isRemote)
				playLittleMaidSound(so, LMM_LittleMaidMobNX.cfg_forceLivingSound);
			else
				playSound(so, LMM_LittleMaidMobNX.cfg_forceLivingSound);
		//	livingSoundTick = 1;
		//}
	}

	@Override
	public void onKillEntity(EntityLivingBase par1EntityLiving) {
		super.onKillEntity(par1EntityLiving);
		if (isBloodsuck()) {
			playLittleMaidSound(LMM_EnumSound.laughter, false);
		} else {
			setAttackTarget(null);
		}
	}

	@Override
	protected boolean canDespawn() {
		// デスポーン判定
		return isTamed()||hasCustomName() ? false : LMM_LittleMaidMobNX.cfg_canDespawn;
	}

	@Override
	public boolean getCanSpawnHere() {
		// スポーン可能か？
		if (LMM_LittleMaidMobNX.cfg_spawnLimit <= getMaidCount()) {
			LMM_LittleMaidMobNX.Debug("Spawn Limit.");
			return false;
		}
		/*
		// TODO:サーバー側で判定できないので意味なし?
		MMM_TextureBox lbox = MMM_TextureManager.instance.getTextureBox(textureBox[0]);
		if (worldObj == null || textureModel == null
				|| !textureBox[0].mo.getCanSpawnHere(worldObj, lx, ly, lz, this)) {
			mod_LMM_littleMaidMob.Debug(String.format("%s is can't spawn hear.", textureName));
			return false;
		}
		*/
		if (LMM_LittleMaidMobNX.cfg_Dominant) {
			// ドミナント
			return this.worldObj.checkNoEntityCollision(this.getEntityBoundingBox())
					&& this.worldObj.getCollidingBoundingBoxes(this, this.getEntityBoundingBox()).isEmpty()
					&& !this.worldObj.isAnyLiquid(this.getEntityBoundingBox())
					/*&& this.getBlockPathWeight(lx, ly, lz) >= 0.0F*/;
		}
		return super.getCanSpawnHere();
	}

	@Override
	public AxisAlignedBB getEntityBoundingBox() {
		return super.getEntityBoundingBox();
	}

	@Override
	public void setDead() {
		if (mstatgotcha != null&&maidAvatar != null) {
			// 首紐をドロップ
			EntityItem entityitem = new EntityItem(worldObj, mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ, new ItemStack(Items.string));
			worldObj.spawnEntityInWorld(entityitem);
			mstatgotcha = null;
		}
		super.setDead();
	}

	/**
	 * 読み込み領域内のメイドさんの数
	 */
	public int getMaidCount() {
		int lj = 0;
		for (int li = 0; li < worldObj.loadedEntityList.size(); li++) {
			if (worldObj.loadedEntityList.get(li) instanceof LMM_EntityLittleMaid) {
				lj++;
			}
		}
		return lj;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		// お子さんの設定
		return null;
	}

	// エフェクト表示
	public void showParticleFX(EnumParticleTypes s) {
		showParticleFX(s, 1D, 1D, 1D);
	}

	public void showParticleFX(EnumParticleTypes s, double d, double d1, double d2) {
		showParticleFX(s, d, d1, d2, 0D, 0D, 0D);
	}

	public void showParticleFX(EnumParticleTypes s, double d, double d1, double d2, double d3, double d4, double d5 ) {
		for (int i = 0; i < 7; i++) {
			double d6 = rand.nextGaussian() * d + d3;
			double d7 = rand.nextGaussian() * d1 + d4;
			double d8 = rand.nextGaussian() * d2 + d5;

			worldObj.spawnParticle(s, (posX + rand.nextFloat() * width * 2.0F) - width, posY + 0.5D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, d6, d7, d8);
		}
	}

	@Override
	public void handleHealthUpdate(byte par1) {
		// worldObj.setEntityState(this, (byte))で指定されたアクションを実行
		switch (par1) {
		case 10:
			// 不機嫌
			showParticleFX(EnumParticleTypes.SMOKE_NORMAL, 0.02D, 0.02D, 0.02D);
			break;
		case 11:
			// ゴキゲン
			double a = getContractLimitDays() / 7D;
			double d6 = a * 0.3D;
			double d7 = a;
			double d8 = a * 0.3D;

			worldObj.spawnParticle(EnumParticleTypes.NOTE, posX, posY + height + 0.1D, posZ, d6, d7, d8);
			break;
		case 12:
			// 自由行動
			showParticleFX(EnumParticleTypes.REDSTONE, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
			break;
		case 13:
			// 不自由行動
			showParticleFX(EnumParticleTypes.SMOKE_NORMAL, 0.02D, 0.02D, 0.02D);
			break;
		case 14:
			// トレーサー
			showParticleFX(EnumParticleTypes.EXPLOSION_NORMAL, 0.3D, 0.3D, 0.3D, 0.0D, 0.0D, 0.0D);
			break;

		default:
			super.handleHealthUpdate(par1);
		}
	}

	// ポーション効果のエフェクト
	@Override
	public void setAbsorptionAmount(float par1) {
		// AbsorptionAmount
		if (par1 < 0.0F) {
			par1 = 0.0F;
		}

		this.getDataWatcher().updateObject(dataWatch_Absoption, Float.valueOf(par1));
	}
	@Override
	public float getAbsorptionAmount() {
		return this.getDataWatcher().getWatchableObjectFloat(dataWatch_Absoption);
	}


	public int colorMultiplier(float pLight, float pPartialTicks) {
		// 発光処理用
		int lbase = 0, i = 0;
		if (maidOverDriveTime.isDelay()) {
			if (maidOverDriveTime.getValue() > 0) {
				i = 64;
			}else{
				i = (int) (128 - maidOverDriveTime.getValue() * (128f / LMM_LittleMaidMobNX.cfg_maidOverdriveDelay));
			}
			LMM_LittleMaidMobNX.Debug("COUNT %d", maidOverDriveTime.getValue());
		}else{
			i = 0;
		}
		lbase = i << 24 | 0x00df0f0f;

		if (isActiveModeClass()) {
			lbase = lbase | getActiveModeClass().colorMultiplier(pLight, pPartialTicks);
		}

		return lbase;
	}


	// AI関連
	protected boolean isAIEnabled() {
		// 新AI対応
		return true;
	}

	/**
	 * 敵味方識別
	 */
	public boolean getIFF(Entity pEntity) {
		// 敵味方識別(敵=false)
		if (pEntity == null || pEntity == mstatMasterEntity) {
			return true;
		}

		int tt = LMM_IFF.getIFF(getMaidMaster(), pEntity);
		switch (tt) {
		case LMM_IFF.iff_Enemy:
			return false;
		case LMM_IFF.iff_Friendry:
			return true;
		case LMM_IFF.iff_Unknown:
			if (isBloodsuck()) {
				// 血に餓えている時は敵
				return false;
			}
			if (pEntity instanceof LMM_EntityLittleMaid) {
				// お遊びモードのメイドには敵対しない
				if (((LMM_EntityLittleMaid)pEntity).mstatPlayingRole > LMM_EntityMode_Playing.mpr_NULL) {
					return true;
				}
			}
			if (pEntity instanceof EntityCreature) {
				// 相手が何をターゲットにしているかで決まる
				Entity et = ((EntityCreature)pEntity).getAttackTarget();
				if (et != null && et == mstatMasterEntity) {
					return false;
				}
				if (et == this) {
					return false;
				}
				if (et instanceof LMM_EntityLittleMaid) {
					// 同じマスターのメイドを攻撃対象としている
					if (((LMM_EntityLittleMaid)et).getMaidMasterEntity() == mstatMasterEntity) {
						return false;
					}
				}
			}
			return true;

		default :
			return false;
		}
	}

	@Override
	public boolean canAttackClass(Class par1Class) {
		// IFFの設定、クラス毎の判定しかできないので使わない。
		return true;
	}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity) {
		//反撃設定
		if(par1Entity instanceof EntityMob && !(par1Entity instanceof EntityCreeper)){
			((EntityMob) par1Entity).setAttackTarget(this);
			((EntityMob) par1Entity).setRevengeTarget(this);
			((EntityMob) par1Entity).getNavigator().setPath(getNavigator().getPath(), ((EntityMob)par1Entity).moveForward);
		}

		// 正常時は回復優先処理
		if (getHealth() < 10 && !isBloodsuck() && LMMNX_API_Item.hasSugar(this)) {
			return true;
		}

		// 特殊な攻撃処理
		if (isActiveModeClass() && getActiveModeClass().attackEntityAsMob(maidMode, par1Entity)) {
			return true;
		}

		// 標準処理
		setSwing(20, isBloodsuck() ? LMM_EnumSound.attack_bloodsuck : LMM_EnumSound.attack, !isPlaying());
		maidAvatar.attackTargetEntityWithCurrentItem(par1Entity);
		return true;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {
		// お好みは何？
		if (isContractEX()) {
			return LMMNX_API_Item.isSugar(par1ItemStack.getItem());
		}
		return par1ItemStack.getItem() == Items.cake;
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		// データセーブ
		super.writeEntityToNBT(par1nbtTagCompound);

		par1nbtTagCompound.setTag("Inventory", maidInventory.writeToNBT(new NBTTagList()));
		par1nbtTagCompound.setString("Mode", getMaidModeString(mstatWorkingInt));
		par1nbtTagCompound.setBoolean("Wait", isMaidWait());
		par1nbtTagCompound.setBoolean("Freedom", isFreedom());
		par1nbtTagCompound.setBoolean("Tracer", isTracer());
		par1nbtTagCompound.setBoolean("isWildSaved", isWildSaved);
		par1nbtTagCompound.setInteger("LimitCount", maidContractLimit);
		par1nbtTagCompound.setLong("Anniversary", maidAnniversary);
		par1nbtTagCompound.setInteger("EXP", experienceValue);
		par1nbtTagCompound.setInteger("DominantArm", maidDominantArm);
		par1nbtTagCompound.setInteger("Color", textureData.getColor());
		par1nbtTagCompound.setString("texName", textureData.getTextureName(0));
		par1nbtTagCompound.setString("texArmor", textureData.getTextureName(1));
		par1nbtTagCompound.setBoolean("isSwimming", swimmingEnabled);
		par1nbtTagCompound.setInteger("maidArmorVisible", maidArmorVisible);
		if(textureModelNameForClient==null) textureModelNameForClient = "default_Orign";
		par1nbtTagCompound.setString("textureModelNameForClient", textureModelNameForClient);
		if(textureArmorNameForClient==null) textureArmorNameForClient = "default_Orign";
		par1nbtTagCompound.setString("textureArmorNameForClient", textureArmorNameForClient);
		par1nbtTagCompound.setBoolean("isMadeTextureNameFlag", isMadeTextureNameFlag);

		NBTTagCompound prevtargettag = new NBTTagCompound();
		par1nbtTagCompound.setTag("prevtarget", prevtargettag);
		// HomePosition
		par1nbtTagCompound.setInteger("homeX", func_180486_cf().getX());
		par1nbtTagCompound.setInteger("homeY", func_180486_cf().getY());
		par1nbtTagCompound.setInteger("homeZ", func_180486_cf().getZ());
		par1nbtTagCompound.setInteger("homeWorld", homeWorld);
		// Tiles
		NBTTagCompound lnbt = new NBTTagCompound();
		par1nbtTagCompound.setTag("Tiles", lnbt);
		for (int li = 0; li < maidTiles.length; li++) {
			if (maidTiles[li] != null) {
				lnbt.setIntArray(String.valueOf(li), maidTiles[li]);
			}
		}
		// 追加分
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			maidEntityModeList.get(li).writeEntityToNBT(par1nbtTagCompound);
		}
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		// データロード
		if(maidAvatar==null) return;
		super.readEntityFromNBT(par1nbtTagCompound);

		if (par1nbtTagCompound.hasKey("ModeColor")) {
			// 旧版からの継承
			String s = par1nbtTagCompound.getString("Master");
			if(s.length() > 0) {
				W_Common.setOwner(this, s);
				setContract(true);
			}
			NBTTagList nbttaglist = par1nbtTagCompound.getTagList("Inventory", 10);
			maidInventory.readFromNBT(nbttaglist);
			// アーマースロット変更に対応するためのコード
			ItemStack[] armi = new ItemStack[4];
			for (int i = 0; i < 4; i++) {
				ItemStack is = maidInventory.armorItemInSlot(i);
				if (is != null) {
					armi[3 - ((ItemArmor)is.getItem()).armorType] = is;
				}
			}
			maidInventory.armorInventory = armi;
			//
			setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
			setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
			setTracer(par1nbtTagCompound.getBoolean("Tracer"));
			textureData.textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texName"));
			textureData.textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texArmor"));
			textureData.textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureData.textureIndex[0]);
			textureData.textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureData.textureIndex[1]);

			byte b = par1nbtTagCompound.getByte("ModeColor");
			setColor(b & 0x0f);
			switch ((b & 0xf0) >> 4) {
			case 0:
				setMaidMode(0x0000);	// Wild
				break;
			case 2:
				setMaidMode(0x0001);	// Escorter
				break;
			case 4:
				setMaidMode(0x0080);	// Fencer
				break;
			case 5:
				setMaidMode(0x0000);	// Healer
				break;
			case 6:
				setMaidMode(0x0021);	// Cooking
				break;
			case 7:
				setMaidMode(0x00c0);	// Bloodsucker
				break;
			case 8:
				setMaidMode(0x0083);	// Archer
				break;
			case 9:
				setMaidMode(0x00c3);	// Blazingstar
				break;
			case 10:
				setMaidMode(0x0081);	// Ripper
				break;
			case 11:
				setMaidMode(0x00c2);	// Detonator
				break;
			case 12:
				setMaidMode(0x00c1);	// TNT-D
				break;
			case 13:
				setMaidMode(0x0020);	// Torcher
				break;
			case 15:
				setMaidMode(0x0000);	// Pharmacist
				break;
			default :
				setMaidMode(0x0000);	// Wild
			}
//			setMaidMode((b & 0xf0) >> 4);
			int lhx = MathHelper.floor_double(posX);
			int lhy = MathHelper.floor_double(posY);
			int lhz = MathHelper.floor_double(posZ);;
//			func_110172_bL().set(lhx, lhy, lhz);
			func_175449_a(new BlockPos(lhx,lhy,lhz),(int)getMaximumHomeDistance());
			long lcl = par1nbtTagCompound.getLong("Limit");
			if (isContract() && lcl == 0) {
				maidContractLimit = 24000;
			} else {
				maidContractLimit = (int)((lcl - worldObj.getTotalWorldTime()));
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isContract()) {
				// ダミーの数値を入れる
				maidAnniversary = worldObj.getWorldTime() - getEntityId();
			}

		} else {
			// 新型
			LMM_LittleMaidMobNX.Debug("read." + worldObj.isRemote);

			maidInventory.readFromNBT(par1nbtTagCompound.getTagList("Inventory", 10));
			setMaidWait(par1nbtTagCompound.getBoolean("Wait"));
			setFreedom(par1nbtTagCompound.getBoolean("Freedom"));
			setTracer(par1nbtTagCompound.getBoolean("Tracer"));
			setMaidMode(par1nbtTagCompound.getString("Mode"));
			if (par1nbtTagCompound.hasKey("LimitCount")) {
				maidContractLimit = par1nbtTagCompound.getInteger("LimitCount");
			} else {
				long lcl = par1nbtTagCompound.getLong("Limit");
				if (isContract() && lcl == 0) {
					maidContractLimit = 24000;
				} else {
					maidContractLimit = (int)((lcl - worldObj.getWorldTime()));
				}
			}
			if (isContract() && maidContractLimit == 0) {
				// 値がおかしい時は１日分
//				maidContractLimit = worldObj.getWorldTime() + 24000L;
				maidContractLimit = 24000;
			}
			maidAnniversary = par1nbtTagCompound.getLong("Anniversary");
			if (maidAnniversary == 0L && isContract()) {
				// ダミーの数値を入れる
				maidAnniversary = worldObj.getWorldTime() - getEntityId();
			}
			if (maidAvatar != null) {
				maidAvatar.experienceTotal = par1nbtTagCompound.getInteger("EXP");
			}
			setDominantArm(par1nbtTagCompound.getInteger("DominantArm"));
			if (mstatSwingStatus.length <= maidDominantArm) {
				maidDominantArm = 0;
			}
			textureData.textureIndex[0] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texName"));
			textureData.textureIndex[1] = MMM_TextureManager.instance.getIndexTextureBoxServer(this, par1nbtTagCompound.getString("texArmor"));
			textureData.textureBox[0] = MMM_TextureManager.instance.getTextureBoxServer(textureData.textureIndex[0]);
			textureData.textureBox[1] = MMM_TextureManager.instance.getTextureBoxServer(textureData.textureIndex[1]);
			textureData.setColor(par1nbtTagCompound.getInteger("Color"));
			setTexturePackIndex(textureData.color, textureData.getTextureIndex());

			// HomePosition
			int lhx = par1nbtTagCompound.getInteger("homeX");
			int lhy = par1nbtTagCompound.getInteger("homeY");
			int lhz = par1nbtTagCompound.getInteger("homeZ");
//			func_110172_bL().set(lhx, lhy, lhz);
			func_175449_a(new BlockPos(lhx, lhy, lhz),(int)getMaximumHomeDistance());
			homeWorld = par1nbtTagCompound.getInteger("homeWorld");

			// Tiles
			NBTTagCompound lnbt = par1nbtTagCompound.getCompoundTag("Tiles");
			for (int li = 0; li < maidTiles.length; li++) {
				int ltile[] = lnbt.getIntArray(String.valueOf(li));
				maidTiles[li] = ltile.length > 0 ? ltile : null;
			}

			// 追加分
			for (int li = 0; li < maidEntityModeList.size(); li++) {
				maidEntityModeList.get(li).readEntityFromNBT(par1nbtTagCompound);
			}
		}
		textureModelNameForClient = par1nbtTagCompound.getString("textureModelNameForClient");
		if(textureModelNameForClient.equals("")){
			textureModelNameForClient = "default_Orign";
		}
		textureArmorNameForClient = par1nbtTagCompound.getString("textureArmorNameForClient");
		if(textureArmorNameForClient.equals("")){
			textureArmorNameForClient = "default_Orign";
		}

		isMadeTextureNameFlag = par1nbtTagCompound.getBoolean("isMadeTextureNameFlag");

		LMM_LittleMaidMobNX.Debug("READ %s %s", textureModelNameForClient, textureArmorNameForClient);
		if(!worldObj.isRemote) recallRenderParamTextureName(textureModelNameForClient, textureArmorNameForClient);

		onInventoryChanged();
		isWildSaved = par1nbtTagCompound.getBoolean("isWildSaved");
		setSwimming(par1nbtTagCompound.getBoolean("isSwimming"));
		syncSwimming();
		setMaidArmorVisible(par1nbtTagCompound.hasKey("maidArmorVisible")?par1nbtTagCompound.getInteger("maidArmorVisible"):15);
		syncMaidArmorVisible();
		syncFreedom();

	}

	@Override
	public boolean canBePushed()
	{
		// --------------------------------------------
		// 肩車状態でプレイヤーが馬に乗っているときは、当たり判定をなくす。
		if(isMaidWait()) return false;
		if (ridingEntity != null && ridingEntity == mstatMasterEntity) {
			if(ridingEntity.ridingEntity instanceof EntityHorse)
			{
				return false;
			}
		}
		// --------------------------------------------

		return !this.isDead;
	}

	// おんぶおばけは無敵
	@Override
	public boolean canBeCollidedWith() {
		if (ridingEntity != null && ridingEntity == mstatMasterEntity) {
			ItemStack litemstack = mstatMasterEntity.getCurrentEquippedItem();
			return (litemstack == null) || (litemstack.getItem() == Items.saddle);
		}
		return super.canBeCollidedWith();
	}

	@Override
	public boolean canAttackWithItem() {
		if (ridingEntity != null && ridingEntity == mstatMasterEntity) {
			return false;
		}
		return super.canAttackWithItem();
	}

	@Override
	public double getMountedYOffset() {
		// TODO:ここは要調整
		if (riddenByEntity instanceof EntityChicken) {
			return height + 0.03D;
		}
		if (riddenByEntity instanceof EntitySquid) {
			return height - 0.2D;
		}
		return super.getMountedYOffset() + 0.35D;
	}

	/*
	@Override
	public double getYOffset() {
		if(ridingEntity instanceof EntityPlayer) {
			// 姿勢制御
//			setSneaking(true);
//			mstatAimeBow = true;
//			updateAimebow();
//			return (double)(yOffset - 1.8F);

			// --------------------------------------------
			// プレイヤーが馬に乗っているときは、肩車ではなく馬の後ろに乗る
			if(ridingEntity.ridingEntity instanceof EntityHorse)
			{
				if(this.worldObj.isRemote)
				{
					return (double)(yOffset - 2.8F);
				}
				else
				{
					return (double)(yOffset - 1.0F);
				}
			}
			// プレイヤーに肩車
			else
			{
				return (double)(yOffset - 2.0F);
			}
			// --------------------------------------------
		}
		return (double)(yOffset - 0.25F);
	}
	*/


	@Override
	public void updateRidden() {
		// TODO:アップデート時にチェック
		++ticksExisted;
		//

		if(ridingEntity instanceof EntityPlayer) {
			EntityPlayer lep = (EntityPlayer)ridingEntity;

			// ヘッドハガー
			renderYawOffset = lep.renderYawOffset;
			prevRenderYawOffset = lep.prevRenderYawOffset;
			double llpx = lastTickPosX;
			double llpy = lastTickPosY;
			double llpz = lastTickPosZ;

			// ★注意：水に触れると ridingEntity はnullになる ★
			super.updateRidden();

			renderYawOffset = lep.renderYawOffset;
			if (((rotationYaw - renderYawOffset) % 360F) > 90F) {
				rotationYaw = renderYawOffset + 90F;
			}
			if (((rotationYaw - renderYawOffset) % 360F) < -90F) {
				rotationYaw = renderYawOffset - 90F;
			}
			if (((rotationYawHead - renderYawOffset) % 360F) > 90F) {
				rotationYawHead = renderYawOffset + 90F;
			}
			if (((rotationYawHead - renderYawOffset) % 360F) < -90F) {
				rotationYawHead = renderYawOffset - 90F;
			}

			double dx, dz;
			// --------------------------------------------
			// プレイヤーが馬に乗っているときは、肩車ではなく馬の後ろに乗る
			// ridingEntity はsuper.updateRidden();によってNULLになる事があるので注意
			if(lep.ridingEntity instanceof EntityHorse)
			{
				EntityHorse horse = (EntityHorse)lep.ridingEntity;
				if(this.worldObj.isRemote)
				{
					dx = Math.sin((horse.renderYawOffset * Math.PI) / 180D) * 0.5;
					dz = Math.cos((horse.renderYawOffset * Math.PI) / 180D) * 0.5;
				}
				else
				{
					dx = Math.sin((horse.renderYawOffset * Math.PI) / 180D) * 0.9;
					dz = Math.cos((horse.renderYawOffset * Math.PI) / 180D) * 0.9;
				}
			}
			else
			{
				dx = Math.sin((lep.renderYawOffset * Math.PI) / 180D) * 0.35;
				dz = Math.cos((lep.renderYawOffset * Math.PI) / 180D) * 0.35;
			}
			// --------------------------------------------

			setPosition(lep.posX + dx, posY, lep.posZ - dz);
			lastTickPosX = llpx;
			lastTickPosY = llpy;
			lastTickPosZ = llpz;
		} else {
			super.updateRidden();
		}
	}

	@Override
	public void updateRiderPosition() {
		super.updateRiderPosition();
	}

	@Override
	public float getSwingProgress(float par1) {
		for (LMM_SwingStatus lswing : mstatSwingStatus) {
			lswing.getSwingProgress(par1);
		}
		return getSwingStatusDominant().onGround;
	}

	// 首周り
	public void setLooksWithInterest(boolean f) {
		if (looksWithInterest != f) {
			looksWithInterest = f;

			int li = dataWatcher.getWatchableObjectInt(dataWatch_Flags);
			li = looksWithInterest ? (li | dataWatch_Flags_looksWithInterest) : (li & ~dataWatch_Flags_looksWithInterest);
			li = looksWithInterestAXIS ? (li | dataWatch_Flags_looksWithInterestAXIS) : (li & ~dataWatch_Flags_looksWithInterestAXIS);
			dataWatcher.updateObject(dataWatch_Flags, Integer.valueOf(li));
		}
	}

	public boolean getLooksWithInterest() {
		looksWithInterest = (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_looksWithInterest) > 0;
		looksWithInterestAXIS = (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_looksWithInterestAXIS) > 0;

		return looksWithInterest && !isHeadMount();
	}

	public float getInterestedAngle(float f) {
		if(maidInventory.mainInventory[17]!=null){
			if(maidInventory.mainInventory[17].getItem() instanceof ItemArmor){
				if(((ItemArmor)maidInventory.mainInventory[17].getItem()).armorType==0)
					return 0f;
			}
		}
		return (prevRotateAngleHead + (rotateAngleHead - prevRotateAngleHead) * f) * ((looksWithInterestAXIS ? 0.08F : -0.08F) * (float)Math.PI);
	}


	// ダメージコントロール
//	@Override
	public boolean isBlocking() {
		return getSwingStatusDominant().isBlocking();
//		return maidAvatar.isBlocking();
	}

	@Override
	protected void damageArmor(float pDamage) {
		maidInventory.damageArmor(pDamage);
		getAvatarIF().W_damageArmor(pDamage);
	}

	@Override
	public int getTotalArmorValue() {
		return maidAvatar.getTotalArmorValue();
	}

	@Override
	protected float applyArmorCalculations(DamageSource par1DamageSource, float par2) {
		return getAvatarIF().W_applyArmorCalculations(par1DamageSource, par2);
	}

	@Override
	protected float applyPotionDamageCalculations(DamageSource par1DamageSource, float par2) {
		return getAvatarIF().W_applyPotionDamageCalculations(par1DamageSource, par2);
	}

	@Override
	protected void damageEntity(DamageSource par1DamageSource, float par2) {
		// ダメージソースに応じて音声変更
		if (par1DamageSource == DamageSource.fall) {
			maidDamegeSound = LMM_EnumSound.hurt_fall;
			if (isContractEX() && par2>=19 && par2<getHealth()) {
				getMaidMasterEntity().triggerAchievement(LMMNX_Achievements.ac_Ashikubi);
			}
		}
		if(!par1DamageSource.isUnblockable() && isBlocking()) {
			// ブロッキング
//			par2 = (1.0F + par2) * 0.5F;
			LMM_LittleMaidMobNX.Debug(String.format("Blocking success ID:%d, %f -> %f" , this.getEntityId(), par2, (par2 = (1.0F + par2) * 0.5F)));
			maidDamegeSound = LMM_EnumSound.hurt_guard;
		}
		//デバッグ
		//maidInventory.armorInventory[2] = null;

		// 被ダメ
		float llasthealth = getHealth();
		if (par2 > 0 && getActiveModeClass() != null && !getActiveModeClass().damageEntity(maidMode, par1DamageSource, par2)) {
			getAvatarIF().W_damageEntity(par1DamageSource, par2);

			// ダメージを受けると待機を解除
			setMaidWait(false);
		}

		/*
		if (llasthealth == getHealth() && maidDamegeSound == LMM_EnumSound.hurt) {
			maidDamegeSound = LMM_EnumSound.hurt_nodamege;
		}
		*/
		LMM_LittleMaidMobNX.Debug(String.format("GetDamage ID:%d, %s, %f/ %f" , this.getEntityId(), par1DamageSource.damageType, llasthealth - getHealth(), par2));
//		super.damageEntity(par1DamageSource, par2);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		Entity entity = par1DamageSource.getEntity();
		boolean force = true;

		if(par1DamageSource.getSourceOfDamage() instanceof EntitySnowball) force = false;

		if(par1DamageSource.getDamageType().equalsIgnoreCase("thrown"))
		{
			if(entity!=null && this.maidAvatar!=null && entity.getEntityId()==this.maidAvatar.getEntityId())
			{
				return false;
			}
		}
		
		LMM_LittleMaidMobNX.Debug("LMM_EntityLittleMaid.attackEntityFrom "+this+"("+this.maidAvatar+") <= "+entity);

		// ダメージソースを特定して音声の設定
		maidDamegeSound = LMM_EnumSound.hurt;
		if (par1DamageSource == DamageSource.inFire || par1DamageSource == DamageSource.onFire || par1DamageSource == DamageSource.lava) {
			maidDamegeSound = LMM_EnumSound.hurt_fire;
		}
		for (LMM_EntityModeBase lm : maidEntityModeList) {
			float li = lm.attackEntityFrom(par1DamageSource, par2);
			if (li > 0) return li == 1 ? false : true;
		}

		setMaidWait(false);
		setMaidWaitCount(0);
		if (par2 > 0) {
			// 遊びは終わりだ！
			setPlayingRole(0);
//			coolingTick  = 200;
			getNextEquipItem();
		}
		// ゲーム難易度によるダメージの補正
		if(isContract() && (entity instanceof EntityLivingBase) || (entity instanceof EntityArrow)) {
			if(worldObj.getDifficulty() == EnumDifficulty.PEACEFUL) {
				par2 = 0;
			}
			if(worldObj.getDifficulty() == EnumDifficulty.EASY && par2 > 0) {
				par2 = par2 / 2 + 1;
			}
			if(worldObj.getDifficulty() == EnumDifficulty.HARD) {
				par2 = MathHelper.floor_float(par2 * 1.5f);
			}
		}

//		if (par2 == 0 && maidMode != mmode_Detonator) {
		if (par2 == 0) {
			// ノーダメージ
			if (maidDamegeSound == LMM_EnumSound.hurt) {
				maidDamegeSound = LMM_EnumSound.hurt_nodamege;
			}
			playLittleMaidSound(maidDamegeSound, force);
			return false;
		}

		if(super.attackEntityFrom(par1DamageSource, par2)) {
			//契約者の名前チェックはマルチ用
//			if(force) playSound("game.player.hurt");
			if (isContract() && entity != null) {
				if (getIFF(entity) && !isPlaying()) {
					//1.8検討
					//fleeingTick = 0;
					return true;
				}
			} else if (maidInventory.getCurrentItem() == null) {
				return true;
			}
			//1.8検討
			//fleeingTick = 0;
//			entityToAttack = entity;
			/*
			if (entity != null) {
				setPathToEntity(worldObj.getPathEntityToEntity(this, entityToAttack, 16F, true, false, false, true));
			}
			if (maidMode == mmode_Healer && entity instanceof EntityLiving) {
				// ヒーラーは薬剤で攻撃
				maidInventory.currentItem = maidInventory.getInventorySlotContainItemPotion(true, 0, ((EntityLiving)entity).isEntityUndead() & isMaskedMaid);
			}
			*/
			return true;
		}
		return false;


//		return maidAvatar.attackEntityFrom(par1DamageSource, par2);
	}

	/**
	 * 対象にポーションを使う。
	 */
	public void usePotionTotarget(EntityLivingBase entityliving) {
		ItemStack itemstack = maidInventory.getCurrentItem();
		if (itemstack != null && itemstack.getItem() instanceof ItemPotion) {
			// ポーション効果の発動
			itemstack.stackSize--;
			List list = ((ItemPotion)itemstack.getItem()).getEffects(itemstack);
			if (list != null) {
				PotionEffect potioneffect;
				for (Iterator iterator = list.iterator(); iterator.hasNext(); entityliving.addPotionEffect(new PotionEffect(potioneffect))) {
					potioneffect = (PotionEffect)iterator.next();
				}
			}
			if(itemstack.stackSize <= 0) {
				maidInventory.setInventoryCurrentSlotContents(null);
			}
			maidInventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
		}
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		// メイドさんはお砂糖とココアと不定形の何かでできてるの！
		int k = rand.nextInt(3 + par2);
		for(int j = 0; j <= k; j++) {
			if(rand.nextInt(30) == 0) {
				dropItem(Items.slime_ball, 1);
			}
			if(rand.nextInt(50) == 0) {
				entityDropItem(new ItemStack(Items.dye, 1, 3), 0F);
			}
			dropItem(Items.sugar, 1);
		}

		// インベントリをブチマケロ！
		maidInventory.dropAllItems();
	}

	@Override
	protected Item getDropItem() {
		return Items.sugar;
	}

	@Override
	protected int getExperiencePoints(EntityPlayer par1EntityPlayer) {
		return experienceValue;
	}


	@Override
	public void applyEntityCollision(Entity par1Entity) {
		// 閉所接触回避用
		super.applyEntityCollision(par1Entity);

		if (par1Entity instanceof LMM_EntityLittleMaid) {
			if (((LMM_EntityLittleMaid)par1Entity).aiAvoidPlayer.isActive) {
				aiAvoidPlayer.isActive = true;
			}
		} else if (par1Entity == mstatMasterEntity) {
			aiAvoidPlayer.setActive();
		}
	}

	@Override
	public void updateAITasks()
	{
		super.updateAITasks();
		tasks.onUpdateTasks();
//		if(++playingTick==2){
			for (LMM_EntityModeBase ieml : maidEntityModeList) {
				ieml.updateAITick(getMaidModeInt());
			}
//			playingTick = 0;
//		}
	}

	protected PathEntity prevPathEntity = null;

	/**
	 * バージョンアップで水浮き専用に
	 */
	@Override
	protected void updateAITick() {
		// TODO 自動生成されたメソッド・スタブ
		if(getNavigator().getPath()!=null)
			prevPathEntity = getNavigator().getPath();
		boolean flag = !swimmingEnabled;
		if(isInLava()){
			jump();
			flag = true;
		}
		if(!swimmingEnabled) flag = true;
		if(swimmingEnabled&&isInWater()&&prevPathEntity!=null){
			PathPoint pathPoint = prevPathEntity.getFinalPathPoint();
			if(worldObj.getBlockState(new BlockPos(pathPoint.xCoord,pathPoint.yCoord+2,pathPoint.zCoord)).getBlock().getMaterial()!=Material.water)
				flag = true;
			if(getAir()<=0) flag = true;
		}
		if(flag) super.updateAITick();
	}

	@Override
	protected PathNavigate func_175447_b(World worldIn) {
		// TODO 自動生成されたメソッド・スタブ
		return super.func_175447_b(worldIn);
	}

	@Override
	public void onEntityUpdate() {
		//音声再生
		if(worldObj.isRemote&&!playingSound.isEmpty()){
			float lpitch = LMM_LittleMaidMobNX.cfg_VoiceDistortion ? (rand.nextFloat() * 0.2F) + 0.95F : 1.0F;
			List<LMM_EnumSound> playingSoundTmp = new CopyOnWriteArrayList<LMM_EnumSound>();
			playingSoundTmp.addAll(playingSound);
			
			for(LMM_EnumSound enumsound : playingSoundTmp){
				String s = LMM_SoundManager.instance.getSoundValue(enumsound, textureData.getTextureName(0), textureData.getColor());
				//まさかな…
				if(s==null) return;
				if(!s.isEmpty() && !s.startsWith("minecraft:"))
				{
					s = LMM_LittleMaidMobNX.DOMAIN + ":" + s;
				}
				LMM_LittleMaidMobNX.Debug(String.format("id:%d, se:%04x-%s (%s)", getEntityId(), enumsound.index, enumsound.name(), s));

				if (!s.isEmpty()) {
					worldObj.playSound(posX, posY, posZ, s, getSoundVolume(), lpitch, false);
				}
			}
			playingSound.clear();
//			LMM_LittleMaidMobNX.proxy.playLittleMaidSound(worldObj, posX, posY, posZ, playingSound, getSoundVolume(), lpitch, false);
		}
		super.onEntityUpdate();
	}

	/**
	 * 埋葬対策コピー
	 */
	private boolean isBlockTranslucent(int par1, int par2, int par3) {
		return this.worldObj.getBlockState(new BlockPos(par1, par2, par3)).getBlock().isNormalCube();
	}

	/**
	 * 埋葬対策コピー
	 */
	@Override
	protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
		// EntityPlayerSPのを引っ張ってきた
		int var7 = MathHelper.floor_double(par1);
		int var8 = MathHelper.floor_double(par3);
		int var9 = MathHelper.floor_double(par5);
		double var10 = par1 - var7;
		double var12 = par5 - var9;

		boolean lflag = false;
		for (int li = 0; li < height; li++) {
			lflag |= this.isBlockTranslucent(var7, var8 + li, var9);
		}
		if (lflag) {
			boolean var14 = !this.isBlockTranslucent(var7 - 1, var8, var9) && !this.isBlockTranslucent(var7 - 1, var8 + 1, var9);
			boolean var15 = !this.isBlockTranslucent(var7 + 1, var8, var9) && !this.isBlockTranslucent(var7 + 1, var8 + 1, var9);
			boolean var16 = !this.isBlockTranslucent(var7, var8, var9 - 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 - 1);
			boolean var17 = !this.isBlockTranslucent(var7, var8, var9 + 1) && !this.isBlockTranslucent(var7, var8 + 1, var9 + 1);
			byte var18 = -1;
			double var19 = 9999.0D;

			if (var14 && var10 < var19) {
				var19 = var10;
				var18 = 0;
			}

			if (var15 && 1.0D - var10 < var19) {
				var19 = 1.0D - var10;
				var18 = 1;
			}

			if (var16 && var12 < var19) {
				var19 = var12;
				var18 = 4;
			}

			if (var17 && 1.0D - var12 < var19) {
				var19 = 1.0D - var12;
				var18 = 5;
			}

			float var21 = 0.1F;

			if (var18 == 0) {
				this.motionX = (-var21);
			}

			if (var18 == 1) {
				this.motionX = var21;
			}

			if (var18 == 4) {
				this.motionZ = (-var21);
			}

			if (var18 == 5) {
				this.motionZ = var21;
			}

			return !(var14 | var15 | var16 | var17);
		}

		return false;
	}

	private static final int[] ZBOUND_BLOCKOFFS = new int[]{  1,  1,  0, -1, -1, -1,  0,  1};
	private static final int[] XBOUND_BLOCKOFFS = new int[]{  0, -1, -1, -1,  0,  1,  1,  1};

	public int DEBUGCOUNT = 0;
	private boolean isInsideOpaque = false;

	@Override
	public void onLivingUpdate() {
		// 回復判定
		float lhealth = getHealth();
		if (lhealth > 0) {
			if (!worldObj.isRemote) {
				if (getSwingStatusDominant().canAttack()) {
					if (!isBloodsuck()) {
						// 通常時は回復優先
						if (lhealth < getMaxHealth()) {
							consumeSugar(EnumConsumeSugar.HEAL);
						}
					}
				}
			}
		}

		//雪合戦試験
		if (maidFreedom && worldObj.isDaytime() && !isPlaying() && (maidMode==0||maidMode==1)){
			if(LMM_EntityMode_Playing.checkSnows(
						MathHelper.floor_double(posX),
						MathHelper.floor_double(posY),
						MathHelper.floor_double(posZ), worldObj)){
				setPlayingRole(0x0010);
			}else{
				setPlayingRole(0);
			}
		}

		/*
		if(getMaidModeInt()==LMM_EntityMode_Healer.mmode_Healer){

		}*/

		superLivingUpdate();

		maidInventory.decrementAnimations();

		//壁衝突判定
		//pitchはデフォルト+-180度が北方向(Z負)
		//八方位に分割して割り出す
		if(!worldObj.isRemote){
//			float rot = getRotationYawHead();
			int pitchindex = Math.round(rotationYaw/45F);
//			if(pitchindex>=8||pitchindex<0) pitchindex = 0;
			while(pitchindex<0)  pitchindex+=8;
			while(pitchindex>=8) pitchindex-=8;

			int px = MathHelper.floor_double(posX);
			int pz = MathHelper.floor_double(posZ);
			int py = MathHelper.floor_double(getEntityBoundingBox().minY);
			float movespeed = getAIMoveSpeed();

			BlockPos targetPos = new BlockPos(px+XBOUND_BLOCKOFFS[pitchindex], py, pz+ZBOUND_BLOCKOFFS[pitchindex]);
			new BlockPos(px+XBOUND_BLOCKOFFS[pitchindex], py+1, pz+ZBOUND_BLOCKOFFS[pitchindex]);
			// この辺プレイヤーと同じ判定方法
			if(movespeed!=0 && !isMaidWait() && isCollidedHorizontally && (onGround&&!isInWater()) &&
					0 == WalkNodeProcessor.func_176170_a(worldObj, this, targetPos.getX(), targetPos.getY()  , targetPos.getZ(), MathHelper.floor_float(width+1.0F), MathHelper.floor_float(height+1.0F), MathHelper.floor_float(width+1.0F), false, false, true) &&
					1 == WalkNodeProcessor.func_176170_a(worldObj, this, targetPos.getX(), targetPos.getY()+1, targetPos.getZ(), MathHelper.floor_float(width+1.0F), MathHelper.floor_float(height+1.0F), MathHelper.floor_float(width+1.0F), false, false, true)){
				//段差にギリ載せ
				setLocationAndAngles(posX+0.05*XBOUND_BLOCKOFFS[pitchindex], posY+1D, posZ+0.05*ZBOUND_BLOCKOFFS[pitchindex], rotationYaw, rotationPitch);
			}

			// 埋まった
			OPAQUE: if(isEntityInsideOpaqueBlock()){
				if(!isInsideOpaque) for(int i=2;i<10;i++){
					if(!worldObj.getBlockState(new BlockPos(posX, py+i, posZ)).getBlock().isVisuallyOpaque()&&!worldObj.getBlockState(new BlockPos(posX, py+i+1, posZ)).getBlock().isVisuallyOpaque()){
						setLocationAndAngles(posX, py+i, posZ, rotationYaw, rotationPitch);
						break OPAQUE;
					}
				}
				isInsideOpaque = true;
			}else{
				isInsideOpaque = false;
			}
		}

		ItemStack itemstack = this.getInventory()[0];

		if (itemstack != null)
		{
			if (itemstack.isItemStackDamageable())
			{
				itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));

				if (itemstack.getItemDamage() >= itemstack.getMaxDamage())
				{
					this.renderBrokenItemStack(itemstack);
					// this.setCurrentItemOrArmor(4, (ItemStack)null);
				}
			}

			//flag = false;
		}

		if(lhealth > 0) {
			// 近接監視の追加はここ
			// アイテムの回収
			if (!worldObj.isRemote) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(1.0D, 0.0D, 1.0D));
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						Entity entity = (Entity)list.get(i);
						if (!entity.isDead) {
							if (entity instanceof EntityArrow) {
								// 特殊回収
								((EntityArrow)entity).canBePickedUp = 1;
							}
							entity.onCollideWithPlayer(maidAvatar);
						}
					}
				}
			}
			// 時計を持っている
			// TODO:多分この辺りの処理はおかしい
			if (isContractEX() && mstatClockMaid) {
				// ゲーム内時間に合わせた音声の再生
				mstatTime = (int)(worldObj.getWorldTime() % 24000);
				if (mstatMasterEntity != null) {
					boolean b = mstatMasterEntity.isPlayerSleeping();

					if (mstatMasterDistanceSq < 25D && getEntitySenses().canSee(mstatMasterEntity))	{
						LMM_EnumSound lsound = LMM_EnumSound.Null;
						if (mstatFirstLook && (mstatTime > 23500 || mstatTime < 1500)) {
							lsound = LMM_EnumSound.goodmorning;
							mstatFirstLook = false;
						}
						else if (!mstatFirstLook && b) {
							lsound = LMM_EnumSound.goodnight;
							mstatFirstLook = true;
						}
						else if (mstatFirstLook && !b) {
							mstatFirstLook = false;
						}

						if (lsound != LMM_EnumSound.Null) {
							playLittleMaidSound(lsound, true);
							setLooksWithInterest(true);
						}
					} else {
						if (!mstatFirstLook && (b || (mstatTime > 18000 && mstatTime < 23500))) {
							mstatFirstLook = true;
						}
					}
				}
			} else {
				mstatTime = 6000;
			}

			// TNT-D System
			maidOverDriveTime.onUpdate();
			if (maidOverDriveTime.isDelay()) {
				for (int li = 0; li < mstatSwingStatus.length; li++) {
					mstatSwingStatus[li].attackTime--;
				}
				if (maidOverDriveTime.isEnable()) {
					worldObj.spawnParticle(EnumParticleTypes.REDSTONE, (posX + rand.nextFloat() * width * 2.0F) - width, posY + 0.5D + rand.nextFloat() * height, (posZ + rand.nextFloat() * width * 2.0F) - width, 1.2D, 0.4D, 0.4D);
				}
				if (!worldObj.isRemote) {
					Entity lattackentity = getAttackTarget();
					if (lattackentity == null) {
						lattackentity = getAITarget();
					}
					if (lattackentity != null) {
						PathEntity pe = getNavigator().getPathToEntityLiving(lattackentity);//getPathEntityToEntity(this, lattackentity, 16F, true, false, false, true);
						if (pe != null) {
							pe.incrementPathIndex();
							if (!pe.isFinished()) {
								Vec3 v = pe.getPosition(this);
								setPosition(v.xCoord, v.yCoord, v.zCoord);
							}
						}
					}
				}
			}

			if (!worldObj.isRemote) {
				if (getSwingStatusDominant().canAttack()) {
//					mod_LMM_littleMaidMob.Debug("isRemort:" + worldObj.isRemote);
					// 回復
					if (getHealth() < getMaxHealth()) {
						consumeSugar(EnumConsumeSugar.HEAL);
					}
					// つまみ食い
					if (rand.nextInt(50000) == 0) {
						consumeSugar(EnumConsumeSugar.OTHER);
					}
					// 契約更新
					if (isContractEX()) {
						float f = getContractLimitDays();
						if (f <= 6) {
							// 契約更新
							consumeSugar(EnumConsumeSugar.RECONTRACT);
						}
					}
				}
			}
		}
	}

	@Override
	public void setLocationAndAngles(double x, double y, double z, float yaw, float pitch){
		if(worldObj.isRemote) requestRenderParamRecall();
		super.setLocationAndAngles(x, y, z, yaw, pitch);
	}

	public void setLocationAndAnglesWithResetPath(double x, double y, double z, float yaw, float pitch){
		setLocationAndAngles(x, y, z, yaw, pitch);
		navigator.clearPathEntity();
		resetNavigator();
	}

	private void superLivingUpdate() {
		if(onGround) isJumping = false;

		if (this.newPosRotationIncrements > 0)
		{
			double d0 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
			double d1 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
			double d2 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
			double d3 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.rotationYaw);
			this.rotationYaw = (float)(this.rotationYaw + d3 / this.newPosRotationIncrements);
			this.rotationPitch = (float)(this.rotationPitch + (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
			--this.newPosRotationIncrements;
			this.setPosition(d0, d1, d2);
			this.setRotation(this.rotationYaw, this.rotationPitch);
		}
		else if (!this.isServerWorld())
		{
			this.motionX *= 0.98D;
			this.motionY *= 0.98D;
			this.motionZ *= 0.98D;
		}

		if (Math.abs(this.motionX) < 0.005D)
		{
			this.motionX = 0.0D;
		}

		if (Math.abs(this.motionY) < 0.005D)
		{
			this.motionY = 0.0D;
		}

		if (Math.abs(this.motionZ) < 0.005D)
		{
			this.motionZ = 0.0D;
		}

		this.worldObj.theProfiler.startSection("ai");

		if (this.isMovementBlocked())
		{
			this.isJumping = false;
			this.moveStrafing = 0.0F;
			this.moveForward = 0.0F;
			this.randomYawVelocity = 0.0F;
		}
		else if (this.isServerWorld())
		{
			this.worldObj.theProfiler.startSection("newAi");
			try{
				updateEntityActionState();
			}catch(Exception e){
				e.printStackTrace();
			}
			this.worldObj.theProfiler.endSection();
		}

		this.worldObj.theProfiler.endSection();
		this.worldObj.theProfiler.startSection("jump");

		if (this.isInWater())
		{
			this.updateAITick();
		}
		else if (this.isInLava())
		{
			this.func_180466_bG();
		}

		this.worldObj.theProfiler.endSection();

		this.worldObj.theProfiler.startSection("travel");
		this.moveStrafing *= 0.98F;
		this.moveForward *= 0.98F;
		this.randomYawVelocity *= 0.9F;
		this.moveEntityWithHeading(this.moveStrafing, this.moveForward);
		this.worldObj.theProfiler.endSection();

		this.worldObj.theProfiler.startSection("push");

		if (!this.worldObj.isRemote)
		{
			this.collideWithNearbyEntities();
		}

		this.worldObj.theProfiler.endSection();
	}

	protected void resetNavigator(){
		if(handleWaterMovement()&&swimmingEnabled&&!(navigator instanceof PathNavigateSwimmer)){
//			navigator.clearPathEntity();
			navigator = new PathNavigateSwimmer(this, worldObj);
		}
		if((!handleWaterMovement()||!swimmingEnabled)&&!(navigator instanceof PathNavigateGround)){
//			navigator.clearPathEntity();
			navigator = new PathNavigateGround(this, worldObj);
		}
	}

	@Override
	public void onUpdate() {
		int litemuse = 0;
//		resetNavigator();
		
		if (!worldObj.isRemote) {
			if (registerTick >= 0) {
				registerTick--;
			}

			if (registerTick == 0) {
				getOwner().addChatMessage(new ChatComponentText(StatCollector.translateToLocal("littleMaidMob.chat.text.cancelregistration")));
			}
		}
		// Entity初回生成時のインベントリ更新用
		// サーバーの方が先に起動するのでクライアント側が更新を受け取れない
		if (firstload > 0) {
			// 初回更新用
			// サーバーの方が先に起動しているので強制読み込みの手順が必要
			if (--firstload == 0) {
				if (worldObj.isRemote) {
					LMM_Net.sendToEServer(this, new byte[] {LMM_Statics.LMN_Server_UpdateSlots, 0, 0, 0, 0});
				} else {
				}
			}
		}

		// 飛び道具用
		weaponFullAuto = false;
		weaponReload = false;

		// 主の確認など
		mstatMasterEntity = getMaidMasterEntity();
		if (mstatMasterEntity != null) {
			mstatMasterDistanceSq = getDistanceSqToEntity(mstatMasterEntity);
		}
		// モデルサイズのリアルタイム変更有り？
		textureData.onUpdate();
		// リアルタイム変動値をアップデート
		if (worldObj.isRemote) {
			// クライアント側
			boolean lupd = false;
			lupd |= updateMaidContract();
			lupd |= updateMaidColor();
//			lupd |= updateTexturePack();
			updateTexturePack();
			if (lupd) {
				requestRenderParamRecall();
//				setTextureNames();
			}
			setMaidMode(dataWatcher.getWatchableObjectShort(dataWatch_Mode));
			setDominantArm(dataWatcher.getWatchableObjectByte(dataWatch_DominamtArm));
			updateMaidFlagsClient();
			updateGotcha();

			// 腕の挙動関連
			litemuse = dataWatcher.getWatchableObjectInt(dataWatch_ItemUse);
			for (int li = 0; li < mstatSwingStatus.length; li++) {
				ItemStack lis = mstatSwingStatus[li].getItemStack(this);
				if ((litemuse & (1 << li)) > 0 && lis != null) {
					mstatSwingStatus[li].setItemInUse(lis, lis.getMaxItemUseDuration(), this);
				} else {
					mstatSwingStatus[li].stopUsingItem(this);
				}
			}
		} else {
			boolean lf;
			// サーバー側
			updateRemainsContract();
			// Overdrive
			lf = maidOverDriveTime.isEnable();
			if (getMaidFlags(dataWatch_Flags_OverDrive) != lf) {
				if (lf) {
					playLittleMaidSound(LMM_EnumSound.TNT_D, true);
				}
				setMaidFlags(lf, dataWatch_Flags_OverDrive);
			}
			// Working!
			lf = mstatWorkingCount.isEnable();
			if (getMaidFlags(dataWatch_Flags_Working) != lf) {
				setMaidFlags(lf, dataWatch_Flags_Working);
			}
			// 拗ねる
			if (!isContractEX() && !isFreedom()) {
				setFreedom(true);
				setMaidWait(false);
			}
			// 移動速度の変更
			IAttributeInstance latt = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
			// 属性を解除
			latt.removeModifier(attCombatSpeed);
			if (isContract()) {
				if (!isFreedom() || (getAITarget() != null || getAttackTarget() != null)) {
					// 属性を設定
					latt.applyModifier(attCombatSpeed);
				}
			}
			// スニーキング判定
			latt.removeModifier(attSneakingSpeed);
			if ((onGround && isSneaking()) || isUsingItem()) {
				latt.applyModifier(attSneakingSpeed);
			}
//			isSprinting()
		}

		// 独自処理用毎時処理
		for (LMM_EntityModeBase leb : maidEntityModeList) {
			leb.onUpdate(maidMode);
		}

		super.onUpdate();
		// SwingUpdate
		LMM_SwingStatus lmss1 = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss1.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss1.swingProgress;
		swingProgressInt = maidAvatar.swingProgressInt = lmss1.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss1.isSwingInProgress;

		// Aveterの毎時処理
		if (maidAvatar != null) {
			getAvatarIF().getValue();
			maidAvatar.onUpdate();
//			maidAvatar.setValue();
		}

		// カウンタ系
		if (mstatWaitCount > 0) {
			if (hasPath()) {
				mstatWaitCount = 0;
			} else {
				mstatWaitCount--;
			}
		}
		if (maidSoundInterval > 0) {
			maidSoundInterval--;
		}

		// くびかしげ
		prevRotateAngleHead = rotateAngleHead;
		if (getLooksWithInterest()) {
			rotateAngleHead = rotateAngleHead + (1.0F - rotateAngleHead) * 0.4F;
		} else {
			rotateAngleHead = rotateAngleHead + (0.0F - rotateAngleHead) * 0.4F;
		}

		if (getAttackTarget() != null || getAITarget() != null) {
			setWorking(true);
		}
		// お仕事カウンター
		mstatWorkingCount.onUpdate();

		// 腕の挙動に関する処理
		litemuse = 0;
		for (int li = 0; li < mstatSwingStatus.length; li++) {
			mstatSwingStatus[li].onUpdate(this);
			if (mstatSwingStatus[li].isUsingItem()) {
				litemuse |= (1 << li);
			}
		}
		// 標準変数に対する数値の代入
		LMM_SwingStatus lmss = getSwingStatusDominant();
		prevSwingProgress = maidAvatar.prevSwingProgress = lmss.prevSwingProgress;
		swingProgress = maidAvatar.swingProgress = lmss.swingProgress;
		swingProgressInt = maidAvatar.swingProgressInt = lmss.swingProgressInt;
		isSwingInProgress = maidAvatar.isSwingInProgress = lmss.isSwingInProgress;

		// 持ち物の確認
		if (maidInventory.inventoryChanged) {
			onInventoryChanged();
			maidInventory.inventoryChanged = false;
		}
		if (!worldObj.isRemote) {
			// サーバー側処理
			// アイテム使用状態の更新
			dataWatcher.updateObject(dataWatch_ItemUse, litemuse);
			// インベントリの更新
//			if (!mstatOpenInventory) {
				for (int li = 0 ;li < maidInventory.getSizeInventory(); li++) {
					boolean lchange = false;
					int lselect = 0xff;
					// 選択装備が変わった
					for (int lj = 0; lj < mstatSwingStatus.length; lj++) {
						lchange = mstatSwingStatus[lj].checkChanged();
						if (mstatSwingStatus[lj].index == li) {
							lselect = lj;
						}
					}
					// インベントリの中身が変わった
					if (lchange || maidInventory.isChanged(li)) {
						ItemStack st = maidInventory.getStackInSlot(li);
						((WorldServer)worldObj).getEntityTracker().func_151248_b(this, new S04PacketEntityEquipment(this.getEntityId(), (li | lselect << 8) + 5, st));
						maidInventory.resetChanged(li);
						LMM_LittleMaidMobNX.Debug(String.format("ID:%d-%s - Slot(%x:%d-%d,%d) Update.", getEntityId(), worldObj.isRemote ? "Client" : "Server", lselect, li, mstatSwingStatus[0].index, mstatSwingStatus[1].index));
					}
//				}
			}

			// 弓構え
			mstatAimeBow &= !getSwingStatusDominant().canAttack();
			// 構えの更新
			updateAimebow();

			// TODO:test
			if (dataWatcher.getWatchableObjectInt(dataWatch_ExpValue) != experienceValue) {
				dataWatcher.updateObject(dataWatch_ExpValue, Integer.valueOf(experienceValue));
			}

			// 自分より大きなものは乗っけない（イカ除く）
			if (riddenByEntity != null && !(riddenByEntity instanceof EntitySquid)) {
				if (height * width < riddenByEntity.height * riddenByEntity.width) {
					if (riddenByEntity instanceof EntityLivingBase) {
						attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)riddenByEntity), 0);
					}
					riddenByEntity.mountEntity(null);
					return;
				}
			}

			// 斧装備時は攻撃力が上がる
			IAttributeInstance latt = this.getEntityAttribute(SharedMonsterAttributes.attackDamage);
			// 属性を解除
			latt.removeModifier(attAxeAmp);
			ItemStack lis = getCurrentEquippedItem();
			if (lis != null && lis.getItem() instanceof ItemAxe) {
				// 属性を設定
				latt.applyModifier(attAxeAmp);
			}
		} else {
			// Client
			// TODO:test
			experienceValue = dataWatcher.getWatchableObjectInt(dataWatch_ExpValue);
		}

		// 紐で拉致
		if(mstatgotcha != null) {
			double d = mstatgotcha.getDistanceSqToEntity(this);
			if (getAttackTarget() == null) {
				// インコムごっこ用
				if (d > 4D) {
//					setPathToEntity(null);
					getNavigator().clearPathEntity();
					getLookHelper().setLookPositionWithEntity(mstatgotcha, 15F, 15F);
				}
				if (d > 12.25D) {
//					setPathToEntity(worldObj.getPathEntityToEntity(mstatgotcha, this, 16F, true, false, false, true));
					getNavigator().tryMoveToXYZ(mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ, 1.0F);
					getLookHelper().setLookPositionWithEntity(mstatgotcha, 15F, 15F);
				}
			}
			if (d > 25D) {
				double d1 = mstatgotcha.posX - posX;
				double d3 = mstatgotcha.posZ - posZ;
				double d5 = 0.125D / (Math.sqrt(d1 * d1 + d3 * d3) + 0.0625D);
				d1 *= d5;
				d3 *= d5;
				motionX += d1;
				motionZ += d3;
			}
			if (d > 42.25D) {
				double d2 = mstatgotcha.posX - posX;
				double d4 = mstatgotcha.posZ - posZ;
				double d6 = 0.0625D / (Math.sqrt(d2 * d2 + d4 * d4) + 0.0625D);
				d2 *= d6;
				d4 *= d6;
				mstatgotcha.motionX -= d2;
				mstatgotcha.motionZ -= d4;
			}
			if (d > 64D) {
				setGotcha(0);
				mstatgotcha = null;
				playSound("random.drr");
			}
			if(rand.nextInt(16) == 0) {
				List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(8D, 8D, 8D));
				for (int k = 0; k < list.size(); k++) {
					Entity entity = (Entity)list.get(k);
					if (!(entity instanceof EntityMob)) {
						continue;
					}
					EntityMob entitymob = (EntityMob)entity;
					if (entitymob.getAttackTarget() == mstatgotcha) {
						//1.8検討
						entitymob.setAttackTarget(this);
						entitymob.getNavigator().setPath(this.getNavigator().getPath(), entitymob.moveForward);
					}
				}
			}
		}

	}


	@Override
	public void onDeath(DamageSource par1DamageSource) {
		super.onDeath(par1DamageSource);

		// 死因を表示
		if (!worldObj.isRemote) {
			// マスター判定失敗するかも？
			if (LMM_LittleMaidMobNX.cfg_DeathMessage && mstatMasterEntity != null) {
				String ls = par1DamageSource.getDamageType();
				Entity lentity = par1DamageSource.getEntity();
				if (lentity != null) {
					if (par1DamageSource.getEntity() instanceof EntityPlayer) {
						ls += ":" + MMM_Helper.getPlayerName((EntityPlayer)lentity);
					} else {
						String lt = EntityList.getEntityString(lentity);
						if (lt != null) {
							ls += ":" + lt;
						}
					}
				}

				// 不具合対応
				// getFormattedText → getUnformattedTextForChat
				// getFormattedText はクライアント専用（描画用）。
				// http://forum.minecraftuser.jp/viewtopic.php?f=13&t=23347&p=212078#p211805
				String lt = getDisplayName().getUnformattedTextForChat();

				ChatComponentText text = new ChatComponentText(String.format("your %s killed by %s", lt, ls));
				mstatMasterEntity.addChatMessage(text);
			}
		}
	}

	// ポーションエフェクト
	@Override
	protected void onNewPotionEffect(PotionEffect par1PotionEffect) {
		super.onNewPotionEffect(par1PotionEffect);
		if (mstatMasterEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP)mstatMasterEntity).playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(this.getEntityId(), par1PotionEffect));
		}
	}

	@Override
	protected void onChangedPotionEffect(PotionEffect par1PotionEffect, boolean par2) {
		super.onChangedPotionEffect(par1PotionEffect, par2);
		// TODO:必要かどうかのチェック
//		if (mstatMasterEntity instanceof EntityPlayerMP) {
//			((EntityPlayerMP)mstatMasterEntity).playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.getEntityId(), par1PotionEffect));
//		}
	}

	@Override
	protected void onFinishedPotionEffect(PotionEffect par1PotionEffect) {
		super.onFinishedPotionEffect(par1PotionEffect);
		if (mstatMasterEntity instanceof EntityPlayerMP) {
			((EntityPlayerMP)mstatMasterEntity).playerNetServerHandler.sendPacket(new S1EPacketRemoveEntityEffect(this.getEntityId(), par1PotionEffect));
		}
	}



	/**
	 *  インベントリが変更されました。
	 */
	public void onInventoryChanged() {
		checkClockMaid();
		checkMaskedMaid();
		checkHeadMount();
		getNextEquipItem();
//		setArmorTextureValue();
	}

	/**
	 * インベントリにある次の装備品を選択
	 */
	public boolean getNextEquipItem() {
		if (worldObj.isRemote) {
			// クライアント側は処理しない
			return false;
		}

		int li;
		if (isActiveModeClass()) {
			li = getActiveModeClass().getNextEquipItem(maidMode);
		} else {
			li = -1;
		}
		setEquipItem(maidDominantArm, li);
		return li > -1;
	}

	public void setEquipItem(int pArm, int pIndex) {
		if (pArm == maidDominantArm) {
			maidInventory.currentItem = pIndex;
		}
		int li = mstatSwingStatus[pArm].index;
		if (li != pIndex) {
			if (li > -1) {
				maidInventory.setChanged(li);
			}
			if (pIndex > -1) {
				maidInventory.setChanged(pIndex);
			}
			mstatSwingStatus[pArm].setSlotIndex(pIndex);
		}
	}
	public void setEquipItem(int pIndex) {
		setEquipItem(maidDominantArm, pIndex);
	}


	/**
	 * 対応型射撃武器のリロード判定
	 */
	public void getWeaponStatus() {
		// 飛び道具用の特殊処理
		ItemStack is = maidInventory.getCurrentItem();
		if (is == null) return;

		try {
			Method me = is.getItem().getClass().getMethod("isWeaponReload", ItemStack.class, EntityPlayer.class);
			weaponReload = (Boolean)me.invoke(is.getItem(), is, maidAvatar);
		}
		catch (NoSuchMethodException e) {
		}
		catch (Exception e) {
		}

		try {
			Method me = is.getItem().getClass().getMethod("isWeaponFullAuto", ItemStack.class);
			weaponFullAuto = (Boolean)me.invoke(is.getItem(), is);
		}
		catch (NoSuchMethodException e) {
		}
		catch (Exception e) {
		}
	}

	// 保持アイテム関連

	/**
	 * 現在の装備品
	 */
	public ItemStack getCurrentEquippedItem() {
		return maidInventory.getCurrentItem();
	}


	@Override
	public ItemStack getHeldItem() {
		return maidInventory.getCurrentItem();
	}

	@Override
	public ItemStack getEquipmentInSlot(int par1) {
		if (par1 == 0) {
			return getHeldItem();
		} else if (par1 < 5) {
			return maidInventory.armorItemInSlot(par1 - 1);
		} else {
			return maidInventory.getStackInSlot(par1 - 5);
		}
	}

	@Override
	public ItemStack getCurrentArmor(int par1) {
		return maidInventory.armorItemInSlot(par1);
	}

	@Override
	public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
		par1 &= 0x0000ffff;
		if (par1 == 0) {
			maidInventory.setInventoryCurrentSlotContents(par2ItemStack);
		} else if (par1 > 0 && par1 < 4) {
			maidInventory.armorInventory[par1 - 1] = par2ItemStack;
			setTextureNames();
		} else if (par1 == 4) {
//			maidInventory.mainInventory[mstatMaskSelect] = mstatMaskSelect > -1 ? par2ItemStack : null;
			if (mstatMaskSelect > -1) {
				maidInventory.mainInventory[mstatMaskSelect] = par2ItemStack;
			}
			setTextureNames();
		} else {
			par1 -= 5;
			// 持ち物のアップデート
			// 独自拡張:普通にスロット番号の通り、上位８ビットは装備スロット
			// par1はShortで渡されるのでそのように。
			int lslotindex = par1 & 0x7f;
			int lequip = (par1 >>> 8) & 0xff;
			maidInventory.setInventorySlotContents(lslotindex, par2ItemStack);
			maidInventory.resetChanged(lslotindex);	// これは意味ないけどな。
			maidInventory.inventoryChanged = true;
//			if (par1 >= maidInventory.mainInventory.length) {
//				LMM_Client.setArmorTextureValue(this);
//			}

			for (LMM_SwingStatus lss: mstatSwingStatus) {
				if (lslotindex == lss.index) {
					lss.index = -1;
				}
			}
			if (lequip != 0xff) {
				setEquipItem(lequip, lslotindex);
//				mstatSwingStatus[lequip].index = lslotindex;
			}
			if (lslotindex >= LMM_InventoryLittleMaid.maxInventorySize) {
				setTextureNames();
			}
			String s = par2ItemStack == null ? null : par2ItemStack.getDisplayName();
			LMM_LittleMaidMobNX.Debug(String.format("ID:%d Slot(%2d:%d):%s", getEntityId(), lslotindex, lequip, s == null ? "NoItem" : s));
		}
	}

	@Override
	public ItemStack[] getInventory() {
		ItemStack[] tmp = new ItemStack[5];
		tmp[0] = null;
		for(int i=0;i<4;i++){
			tmp[i+1] = maidInventory.armorInventory[i];
		}
		return tmp;
	}

	@Override
	public boolean isMovementBlocked() {
		return super.isMovementBlocked();
	}

	protected void checkClockMaid() {
		// 時計を持っているか？
		mstatClockMaid = maidInventory.getInventorySlotContainItem(Items.clock) > -1;
	}
	/**
	 * 時計を持っているか?
	 */
	public boolean isClockMaid() {
		return mstatClockMaid;
	}

	protected void checkMaskedMaid() {
		// インベントリにヘルムがあるか？
		/*
		for (int i = maidInventory.mainInventory.length - 1; i >= 0; i--) {
			ItemStack is = maidInventory.getStackInSlot(i);
			if (is != null && is.getItem() instanceof ItemArmor && ((ItemArmor)is.getItem()).armorType == 0) {
				// ヘルムを持ってる
				mstatMaskSelect = i;
				maidInventory.armorInventory[3] = is;
				if (worldObj.isRemote) {
					setTextureNames();
				}
				return;
			}
		}
		*/

		mstatMaskSelect = -1;
		maidInventory.armorInventory[3] = null;
		return;
	}
	/**
	 * メットを被ってるか
	 */
	public boolean isMaskedMaid() {
		return mstatMaskSelect > -1;
	}

	protected void checkHeadMount() {
		// 追加の頭部装備の判定
		ItemStack lis = maidInventory.getHeadMount();
		mstatPlanter = false;
		mstatCamouflage = false;
		if (lis != null) {
			if (lis.getItem() instanceof ItemBlock) {
				Block lblock = Block.getBlockFromItem(lis.getItem());
//				mstatPlanter =	(lblock instanceof BlockFlower	  && lblock.getRenderType() ==  1) ||
				mstatPlanter =	(lblock.getRenderType() ==  1) ||
								(lblock instanceof BlockDoublePlant && lblock.getRenderType() == 40);
				mstatCamouflage = (lblock instanceof BlockLeaves) || (lblock instanceof BlockPumpkin) || (lblock instanceof BlockStainedGlass);
			} else if (lis.getItem() instanceof ItemSkull) {
				mstatCamouflage = true;
			}
		}
	}
	/**
	 * カモフラージュ！
	 */
	public boolean isCamouflage() {
		return mstatCamouflage;
	}
	/**
	 * 鉢植え状態
	 */
	public boolean isPlanter() {
		return mstatPlanter;
	}

	/**
	 * ポーション等による腕振りモーションの速度補正
	 */
	public int getSwingSpeedModifier() {
		if (isPotionActive(Potion.digSpeed)) {
			return 6 - (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier()) * 1;
		}

		if (isPotionActive(Potion.digSlowdown)) {
			return 6 + (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
		}
		return 6;
	}

	/**
	 * 手持ちアイテムの破壊
	 */
	public void destroyCurrentEquippedItem() {
		maidInventory.setInventoryCurrentSlotContents(null);
	}

	/**
	 * メイドインベントリを開く
	 * @param pEntityPlayer
	 */
	public void displayGUIMaidInventory(EntityPlayer pEntityPlayer) {
		if (!worldObj.isRemote) {
			LMM_GuiCommonHandler.maidServer = this;
			pEntityPlayer.openGui(LMM_LittleMaidMobNX.instance, LMM_GuiCommonHandler.GUI_ID_INVVENTORY, this.worldObj,
					(int)this.posX, (int)this.posY, (int)this.posZ);
		}
		else
		{
			LMM_GuiCommonHandler.maidClient = this;
		}
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer)
	{
		MMMLib.Debug(this.worldObj.isRemote, "LMM_EntityLittleMaid.interact:"+par1EntityPlayer.getGameProfile().getName());
		float lhealth = getHealth();
		ItemStack itemstack1 = par1EntityPlayer.getCurrentEquippedItem();

		// プラグインでの処理を先に行う
		for (int li = 0; li < maidEntityModeList.size(); li++) {
			if (maidEntityModeList.get(li).preInteract(par1EntityPlayer, itemstack1)) {
				return true;
			}
		}
		// しゃがみ時は処理無効
		if (par1EntityPlayer.isSneaking()) {
			return false;
		}
		// ナデリ判定
		if (lhealth > 0F && par1EntityPlayer.riddenByEntity != null && !(par1EntityPlayer.riddenByEntity instanceof LMM_EntityLittleMaid)) {
			// 載せ替え
			par1EntityPlayer.riddenByEntity.mountEntity(this);
			return true;
		}

		if (mstatgotcha == null && par1EntityPlayer.fishEntity == null) {
			if(itemstack1 != null && itemstack1.getItem() == Items.string) {
				// 紐で繋ぐ
				setGotcha(par1EntityPlayer.getEntityId());
				mstatgotcha = par1EntityPlayer;
				MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
				playSound("random.pop");
				return true;
			}

			if (isContract()) {
				// 契約状態
				if (lhealth > 0F && isMaidContractOwner(par1EntityPlayer)) {
					if (itemstack1 != null) {
						// 追加分の処理
						// プラグインでの処理を先に行う
						for (int li = 0; li < maidEntityModeList.size(); li++) {
							if (maidEntityModeList.get(li).interact(par1EntityPlayer, itemstack1)) {
								return true;
							}
						}
						if (isRemainsContract()) {
							// 通常
							if (LMMNX_API_Item.isSugar(itemstack1.getItem())) {
								// モード切替
								boolean cmode = true;
								if(itemstack1.getItem() instanceof LMMNX_IItemSpecialSugar){
									cmode = ((LMMNX_IItemSpecialSugar)itemstack1.getItem()).onSugarInteract(worldObj, par1EntityPlayer, itemstack1, this);
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								eatSugar(true, false, true);
								if(!cmode) return true;
								worldObj.setEntityState(this, (byte)11);

								LMM_LittleMaidMobNX.Debug("give suger." + worldObj.isRemote);
								if (!worldObj.isRemote) {
									setFreedom(isFreedom());
									if (isMaidWait()) {
										// 動作モードの切替
										boolean lflag = false;
										setActiveModeClass(null);
										for (int li = 0; li < maidEntityModeList.size() && !lflag; li++) {
											lflag = maidEntityModeList.get(li).changeMode(par1EntityPlayer);
											if (lflag) {
												setActiveModeClass(maidEntityModeList.get(li));
											}
										}
										if (!lflag) {
											setMaidMode("Escorter");
											setEquipItem(-1);
//											maidInventory.currentItem = -1;
										}
										setMaidWait(false);
										getNextEquipItem();
									} else {
										// 待機
										setMaidWait(true);
									}
								}
								return true;
							}
							else if (itemstack1.getItem()==LMM_LittleMaidMobNX.registerKey &&
									!par1EntityPlayer.worldObj.isRemote) {
								// トリガーセット
								if (registerTick > 0) {
									registerTick = -1;
									par1EntityPlayer.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("littleMaidMob.chat.text.cancelregistration")));
									return true;
								}

								NBTTagCompound tagCompound = itemstack1.getTagCompound();
								if (tagCompound == null) return false;

								String modeString = tagCompound.getString(LMMNX_ItemRegisterKey.RK_MODE_TAG);
								if (modeString.isEmpty()) return false;

								registerMode = modeString;
								registerTick = 200;
								par1EntityPlayer.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("littleMaidMob.chat.text.readyregistration") + registerMode));

								return true;
							} else if (registerTick > 0 && !par1EntityPlayer.worldObj.isRemote) {
								List list = LMM_TriggerSelect.getuserTriggerList(MMM_Helper.getPlayerName(par1EntityPlayer), registerMode);
								Item item = itemstack1.getItem();
								if (item != null) {
									boolean flag = false;
									while(list.remove(item)) flag = true;
									if (!flag) {
										list.add(item);
										par1EntityPlayer.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("littleMaidMob.chat.text.addtrigger") + " " + registerMode + "/+" + Item.itemRegistry.getNameForObject(item).toString()));
									} else {
										par1EntityPlayer.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("littleMaidMob.chat.text.removetrigger") + " " + registerMode + "/-" + Item.itemRegistry.getNameForObject(item).toString()));
									}
								}
								LMM_IFF.saveIFF(MMM_Helper.getPlayerName(par1EntityPlayer));
								registerTick = -1;
								return true;
							}
							else if (itemstack1.getItem() == Items.dye) {
								// カラーメイド
								if (!worldObj.isRemote) {
									setColor(15 - itemstack1.getItemDamage());
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (itemstack1.getItem() == Items.feather) {
								// 自由行動
//								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								if(!worldObj.isRemote){
									setFreedom(!isFreedom());
									syncFreedom();
									worldObj.setEntityState(this, isFreedom() ? (byte)12 : (byte)13);
								}
								return true;
							}
							else if (itemstack1.getItem() == Items.saddle) {
								// 肩車
								if (!worldObj.isRemote) {
									if (ridingEntity == par1EntityPlayer) {
										this.mountEntity(null);
									} else {
										this.mountEntity(par1EntityPlayer);
									}
									return true;
								}
							}
							else if (itemstack1.getItem() == Items.gunpowder) {
								// test TNT-D
								maidOverDriveTime.setValue(itemstack1.stackSize * 10);
								playSound("mob.zombie.infect");
								if (itemstack1.stackSize == 64) {
									getMaidMasterEntity().triggerAchievement(LMMNX_Achievements.ac_Boost);
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, itemstack1.stackSize);
								return true;
							}
							else if (itemstack1.getItem() == Items.book) {
								// IFFのオープン
//								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
//								if (worldObj.isRemote) {
									par1EntityPlayer.openGui(LMM_LittleMaidMobNX.instance,
											LMM_GuiCommonHandler.GUI_ID_IFF,
											this.worldObj,
											(int)this.posX,
											(int)this.posY,
											(int)this.posZ);
//								}
								return true;
							}
							else if ((itemstack1.getItem() == Items.glass_bottle) && (experienceValue >= 5)) {
								// Expボトル
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								if (!worldObj.isRemote) {
									entityDropItem(new ItemStack(Items.experience_bottle), 0.5F);
									experienceValue -= 5;
									if (maidAvatar != null) {
										maidAvatar.experienceTotal -= 5;
									}
								}
								return true;
							}
							else if (itemstack1.getItem() instanceof ItemPotion) {
								// ポーション
								if(!worldObj.isRemote) {
									List list = ((ItemPotion)itemstack1.getItem()).getEffects(itemstack1);
									if (list != null) {
										PotionEffect potioneffect;
										for (Iterator iterator = list.iterator(); iterator.hasNext(); addPotionEffect(new PotionEffect(potioneffect))) {
											potioneffect = (PotionEffect)iterator.next();
										}
									}
								}
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								return true;
							}
							else if (isFreedom() && itemstack1.getItem() == Items.redstone) {
								// Tracer
								MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
								setMaidWait(false);
								setTracer(!isTracer());
								if (isTracer()) {
									worldObj.setEntityState(this, (byte)14);
								} else {
									worldObj.setEntityState(this, (byte)12);
								}
								return true;
							}else if(itemstack1.getItem() == Items.stick){
								if(maidDominantArm==0){
									setDominantArm(1);
								}else{
									setDominantArm(0);
								}
								return true;
							}else if(itemstack1.getItem() == Items.fish){
								if(!worldObj.isRemote){
									setSwimming(!swimmingEnabled);
									syncSwimming();
									par1EntityPlayer.addChatComponentMessage(new ChatComponentText("Swimming mode was set to "+swimmingEnabled));
								}
								return true;
							}
						}
					}
					// メイドインベントリ
					W_Common.setOwner(this, MMM_Helper.getPlayerName(par1EntityPlayer));
					getNavigator().clearPathEntity();
					isJumping = false;
					if(!worldObj.isRemote){
						syncSwimming();
						syncMaidArmorVisible();
						syncFreedom();
					}
					displayGUIMaidInventory(par1EntityPlayer);
					return true;
				}
				if(!isRemainsContract() && itemstack1 != null){
					// ストライキ
					if (itemstack1.getItem() == Items.sugar) {
						// 受取拒否
						worldObj.setEntityState(this, (byte)10);
						return true;
					} else if (itemstack1.getItem() == Items.cake) {
						// 再契約
						MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);
						maidContractLimit = (24000 * 7);
						setFreedom(false);
						setTracer(false);
						setMaidWait(false);
						setMaidMode("Escorter");
						if(!isMaidContractOwner(par1EntityPlayer)){
							// あんなご主人なんか捨てて、僕のもとへおいで(洗脳)
							W_Common.setOwner(this, MMM_Helper.getPlayerName(par1EntityPlayer));
							playLittleMaidSound(LMM_EnumSound.getCake, true);
							worldObj.setEntityState(this, (byte)7);
							maidContractLimit = (24000 * 7);
							maidAnniversary = worldObj.getTotalWorldTime();
						}else{
							// ごめんねメイドちゃん
							worldObj.setEntityState(this, (byte)11);
							playLittleMaidSound(LMM_EnumSound.Recontract, true);
							
						}
						return true;
					}

				}
			} else {
				// 未契約
				if (itemstack1 != null) {
					if (itemstack1.getItem() == Items.cake) {
						// 契約
						MMM_Helper.decPlayerInventory(par1EntityPlayer, -1, 1);

						deathTime = 0;
						if (!worldObj.isRemote) {
							if (LMMNX_Achievements.ac_Contract != null) {
								par1EntityPlayer.triggerAchievement(LMMNX_Achievements.ac_Contract);
							}
							setContract(true);
							W_Common.setOwner(this, MMM_Helper.getPlayerName(par1EntityPlayer));
							setHealth(20);
							setMaidMode("Escorter");
							setMaidWait(false);
							setFreedom(false);
							setPlayingRole(0);
							playLittleMaidSound(LMM_EnumSound.getCake, true);
//							playLittleMaidSound(LMM_EnumSound.getCake, true);
//							playTameEffect(true);
							worldObj.setEntityState(this, (byte)7);
							// 契約記念日と、初期契約期間
							maidContractLimit = (24000 * 7);
							maidAnniversary = worldObj.getTotalWorldTime();
							// テクスチャのアップデート:いらん？
//							LMM_Net.sendToAllEClient(this, new byte[] {LMM_Net.LMN_Client_UpdateTexture, 0, 0, 0, 0});

						}
						return true;
					}
//					worldObj.setEntityState(this, (byte)6);
				}
			}
		} else if (lhealth > 0F && mstatgotcha != null) {
			if (!worldObj.isRemote) {
				EntityItem entityitem = new EntityItem(worldObj, mstatgotcha.posX, mstatgotcha.posY, mstatgotcha.posZ, new ItemStack(Items.string));
				worldObj.spawnEntityInWorld(entityitem);
				setGotcha(0);
				mstatgotcha = null;
			}
			return true;
		}

		return false;
	}

	// メイドの契約設定
	@Override
	public boolean isTamed() {
		return isContract();
	}
	@Override
	public boolean isContract() {
//		return worldObj.isRemote ? maidContract : super.isTamed();
		return super.isTamed();
	}
	public boolean isContractEX() {
		return isContract() && isRemainsContract();
	}

	@Override
	public void setTamed(boolean par1) {
		setContract(par1);
	}
	@Override
	public void setContract(boolean flag) {
		super.setTamed(flag);
		textureData.setContract(flag);
		if (worldObj.isRemote&&flag) {
			isNotColorExistsWild = false;
		} else {
		}
	}

	/**
	 * 契約期間の残りがあるかを確認
	 */
	protected void updateRemainsContract() {
		boolean lflag = false;
		if (maidContractLimit > 0) {
			maidContractLimit--;
			lflag = true;
		}
		if (getMaidFlags(dataWatch_Flags_remainsContract) != lflag) {
			setMaidFlags(lflag, dataWatch_Flags_remainsContract);
		}
	}
	/**
	 * ストライキに入っていないか判定
	 * @return
	 */
	public boolean isRemainsContract() {
		return getMaidFlags(dataWatch_Flags_remainsContract);
	}

	public float getContractLimitDays() {
		return maidContractLimit > 0 ? (maidContractLimit / 24000F) : -1F;
	}

	public boolean updateMaidContract() {
		// 同一性のチェック
		boolean lf = isContract();
		if (textureData.isContract() != lf) {
			textureData.setContract(lf);
			return true;
		}
		return false;
	}

	@Override
	public EntityLivingBase getOwner() {
		return getMaidMasterEntity();
	}
	public String getMaidMaster() {
		return W_Common.getOwnerName(this);
	}

	public EntityPlayer getMaidMasterEntity() {
		// 主を獲得
		if (isContract()) {
			EntityPlayer entityplayer = mstatMasterEntity;
			if (mstatMasterEntity == null || mstatMasterEntity.isDead) {
				String lname;
				// サーバー側ならちゃんとオーナ判定する

				// Minecraftクラスのプレイヤーを取得していたが、サーバには存在しないためプロキシをかませる。サーバならNULL固定
				EntityPlayer clientPlayer = LMM_LittleMaidMobNX.proxy.getClientPlayer();

				if (!LMM_LittleMaidMobNX.proxy.isSinglePlayer()
						|| LMM_LittleMaidMobNX.cfg_checkOwnerName
						|| clientPlayer == null) {
					lname = getMaidMaster();
				} else {
					lname = MMM_Helper.getPlayerName(clientPlayer);
				}
				entityplayer = worldObj.getPlayerEntityByName(lname);
				// とりあえず主の名前を入れてみる
				// TODO:再設定は不可になったので経過観察
//				maidAvatar.username = lname;

				if (entityplayer != null && maidAvatar != null) {
					maidAvatar.capabilities.isCreativeMode = entityplayer.capabilities.isCreativeMode;
				}

			}
			return entityplayer;
		}
		return null;
	}

	public boolean isMaidContractOwner(String pname) {
		return pname.equalsIgnoreCase(MMM_Helper.getPlayerName(mstatMasterEntity));
	}

	public boolean isMaidContractOwner(EntityPlayer pentity) {
		return pentity == getMaidMasterEntity();

//		return pentity == mstatMasterEntity;
	}

	// メイドの待機設定
	public boolean isMaidWait() {
		return maidWait;
	}

	public boolean isMaidWaitEx() {
		return isMaidWait() | (mstatWaitCount > 0) | isOpenInventory();
	}

	public void setMaidWait(boolean pflag) {
		// 待機常態の設定、 isMaidWait系でtrueを返すならAIが勝手に移動を停止させる。
		maidWait = pflag;
		setMaidFlags(pflag, dataWatch_Flags_Wait);
		aiSit.setSitting(pflag);
		//maidWait = pflag;
		isJumping = false;
		setAttackTarget(null);
		setRevengeTarget(null);
		//setPathToEntity(null);
		getNavigator().clearPathEntity();
		setPlayingRole(0);
		if(pflag){
			//setMaidModeAITasks(null,null);
			setWorking(false);
			getNavigator().clearPathEntity();
			clearTilePosAll();
			/*
			func_175449_a(
					new BlockPos(MathHelper.floor_double(lastTickPosX),
					MathHelper.floor_double(lastTickPosY),
					MathHelper.floor_double(lastTickPosZ)), 0);
					*/
		}else{
			setMaidMode(maidMode,false);
		}
		velocityChanged = true;
	}

	public void setMaidWaitCount(int count) {
		mstatWaitCount = count;
	}


	// インベントリの表示関係
	// まさぐれるのは一人だけ
	public void setOpenInventory(boolean flag) {
		mstatOpenInventory = flag;
	}

	public boolean isOpenInventory() {
		return mstatOpenInventory;
	}

	/**
	 * GUIを開いた時にサーバー側で呼ばれる。
	 */
	public void onGuiOpened() {
		setOpenInventory(true);
	}

	/**
	 * GUIを閉めた時にサーバー側で呼ばれる。
	 */
	public void onGuiClosed() {
		setOpenInventory(false);
		int li = maidMode & 0x0080;
		setMaidWaitCount((li == 0) ? 50 : 0);
	}

	// 腕振り
	public void setSwing(int attacktime, LMM_EnumSound enumsound, boolean force) {
		setSwing(attacktime, enumsound, maidDominantArm, force);
	}

	public void setSwing(int pattacktime, LMM_EnumSound enumsound, int pArm, boolean force) {
		mstatSwingStatus[pArm].attackTime = pattacktime;
//		maidAttackSound = enumsound;
//		soundInterval = 0;// いるか？
		if (!weaponFullAuto) {
			setSwinging(pArm, enumsound, force);
		}
		if (!worldObj.isRemote) {
			byte[] lba = new byte[] {
				LMM_Statics.LMN_Client_SwingArm,
				0, 0, 0, 0,
				(byte)pArm,
				0, 0, 0, 0,
				0, 0, 0, 0
			};
			MMM_Helper.setInt(lba, 6, enumsound.index);
			MMM_Helper.setInt(lba, 10, force?1:0);
			LMM_Net.sendToAllEClient(this, lba);
		}
	}

	public void setSwinging(LMM_EnumSound pSound, boolean force) {
		setSwinging(maidDominantArm, pSound, force);
	}
	public void setSwinging(int pArm, LMM_EnumSound pSound, boolean force) {
		if(!pSound.equals(LMM_EnumSound.Null)) playLittleMaidSound(pSound, force);
		if (mstatSwingStatus[pArm].setSwinging()) {
			maidAvatar.swingProgressInt = -1;
//			maidAvatar.swingProgressInt = -1;
			maidAvatar.isSwingInProgress = true;
		}
	}

	public boolean getSwinging() {
		return getSwinging(maidDominantArm);
	}
	public boolean getSwinging(int pArm) {
		return mstatSwingStatus[pArm].isSwingInProgress;
	}

	/**
	 * 利き腕のリロードタイム
	 */
	public LMM_SwingStatus getSwingStatusDominant() {
		return mstatSwingStatus[maidDominantArm];
	}

	public LMM_SwingStatus getSwingStatus(int pindex) {
		return mstatSwingStatus[pindex];
	}


	// 今宵のメイドは血に飢えておる
	public void setBloodsuck(boolean pFlag) {
		mstatBloodsuck = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_Bloodsuck);
	}

	public boolean isBloodsuck() {
		return mstatBloodsuck;
	}


	// 砂糖関連
	public void setLookSuger(boolean pFlag) {
		mstatLookSuger = pFlag;
		setMaidFlags(pFlag, dataWatch_Flags_LooksSugar);
	}

	public boolean isLookSuger() {
		return mstatLookSuger;
	}

	public static enum EnumConsumeSugar{
		/**通常回復**/HEAL,
		/**契約更新**/RECONTRACT,
		/**その他（つまみ食いとか）**/OTHER
	};

	/** 砂糖を食べる。インベントリの左上から消費する。
	 * @param mode EnumConsumeSugar型の定数
	 */
	public void consumeSugar(EnumConsumeSugar mode){
		ItemStack[] stacklist = maidInventory.mainInventory;
		ItemStack stack = null;
		Item item = null;
		int index = -1;
		for(int i=0;i<stacklist.length;i++){
			ItemStack ts = stacklist[i];
			if(ts==null)continue;
			Item ti = ts.getItem();
			if(LMMNX_API_Item.isSugar(ti)){
				stack = ts;
				item = ti;
				index = i;
				break;
			}
		}
		if(item==null||stack==null||index==-1) return;
		if(item==Items.sugar){
			eatSugar(true, true, mode==EnumConsumeSugar.RECONTRACT);
		}else if(item instanceof LMMNX_IItemSpecialSugar){
			//モノグサ実装。良い子の皆さんはちゃんとif使うように…
			eatSugar(((LMMNX_IItemSpecialSugar)item).onSugarEaten(this, mode, stack), true, mode==EnumConsumeSugar.RECONTRACT);
		}
		maidInventory.decrStackSize(index, 1);
	}

	/** 主に砂糖を食べる仕草やその後のこと。
	 * ペロッ・・・これは・・・砂糖ッ！！
	 * @param heal デフォルトの1回復をするか？
	 * @param motion 腕を振るか？
	 * @param recontract 契約延長効果アリ？
	 */
	protected void eatSugar(boolean heal, boolean motion, boolean recontract) {
		if (motion) {
			setSwing(2, (getMaxHealth() - getHealth() <= 1F) ?  LMM_EnumSound.eatSugar_MaxPower : LMM_EnumSound.eatSugar, false);
		}
		int h = hurtResistantTime;
		if(heal) heal(1);
		hurtResistantTime = h;
		playSound("random.pop");
		LMM_LittleMaidMobNX.Debug(("eat Sugar." + worldObj.isRemote));

		if (recontract) {
			// 契約期間の延長
			maidContractLimit += 24000;
			if (maidContractLimit > 168000) {
				maidContractLimit = 168000;	// 24000 * 7
			}
			
			if (worldObj.getTotalWorldTime() - maidAnniversary > 24000 * 365) {
				if (getMaidMasterEntity() != null) getMaidMasterEntity().triggerAchievement(LMMNX_Achievements.ac_MyFavorite);
			}
		}

		// 暫定処理
		if (maidAvatar != null) {
			maidAvatar.getFoodStats().addStats(20, 20F);
		}
	}


	// お仕事チュ
	/**
	 * 仕事中かどうかの設定
	 */
	public void setWorking(boolean pFlag) {
		mstatWorkingCount.setEnable(pFlag);
	}

	/**
	 * 仕事中かどうかを返す
	 */
	public boolean isWorking() {
		return mstatWorkingCount.isEnable();
	}

	/**
	 * 仕事が終了しても余韻を含めて返す
	 */
	public boolean isWorkingDelay() {
		return mstatWorkingCount.isDelay();
	}

	/**
	 * トレーサーモードの設定
	 */
	public void setTracer(boolean pFlag) {
		maidTracer = pFlag;
		aiTracer.setEnable(pFlag);
		setMaidFlags(pFlag, dataWatch_Flags_Tracer);
//		if (maidTracer) {
//			setFreedom(true);
//		}
		aiTracer.setEnable(pFlag);
	}

	/**
	 * トレーサーモードであるか？
	 */
	public boolean isTracer() {
		return getMaidFlags(dataWatch_Flags_Tracer);
	}


	// お遊びモード
	public void setPlayingRole(int pValue) {

		if (mstatPlayingRole != pValue) {
			mstatPlayingRole = pValue;
			if (pValue == 0) {
				setAttackTarget(null);
				setMaidMode(mstatWorkingInt , true);
			} else {
				setMaidMode(0x00ff, true);
			}
		}
	}

	public int getPlayingRole() {
		return mstatPlayingRole;
	}

	public boolean isPlaying() {
		return mstatPlayingRole != 0;
	}


	// 自由行動
	public void setFreedom(boolean pFlag) {
		// AI関連のリセットもここで。
		maidFreedom = pFlag;
		aiRestrictRain.setEnable(pFlag);
		aiFreeRain.setEnable(pFlag);
		aiWander.setEnable(pFlag);
//		aiJumpTo.setEnable(!pFlag);
		aiAvoidPlayer.setEnable(!pFlag);
		aiFollow.setEnable(!pFlag);
		aiTracer.setEnable(isTracer()&&pFlag);
//		setAIMoveSpeed(pFlag ? moveSpeed_Nomal : moveSpeed_Max);
//		setMoveForward(0.0F);
		setPlayingRole(0);
		if (maidFreedom && isContract()) {
			setTracer(isTracer());
			func_175449_a(
//			setHomeArea(
					new BlockPos(MathHelper.floor_double(posX),
					MathHelper.floor_double(posY),
					MathHelper.floor_double(posZ)), 16);
		} else {
			detachHome();
		}
		setMaidFlags(maidFreedom, dataWatch_Flags_Freedom);
	}

	public boolean isFreedom() {
		return maidFreedom;
	}

	protected void syncFreedom() {
		byte[] b = new byte[]{
			LMMNX_NetSync.LMMNX_Sync,
			0, 0, 0, 0,
			LMMNX_NetSync.LMMNX_Sync_UB_Freedom, (byte)(isFreedom()?1:0)
		};
		syncNet(b);
	}

	public boolean isHeadMount(){
		return ItemUtil.isHelm(maidInventory.mainInventory[17]);
	}
	
	public ItemStack getHeadMountStackCopy(){
		ItemStack stack = maidInventory.mainInventory[17];
		if(ItemUtil.isHelm(stack)) return stack.copy();
		return null;
	}

	/**
	 * サーバーへテクスチャパックのインデックスを送る。
	 * クライアント側の処理
	 */
	protected boolean sendTextureToServer() {
		// 16bitあればテクスチャパックの数にたりんべ
//		MMM_TextureManager.instance.postSetTexturePack(this, textureData.getColor(), textureData.getTextureBox());
		return true;
	}

	private boolean checkedTextureUpdate = false;
	public boolean updateTexturePack() {
		// テクスチャパックが更新されていないかをチェック
		// クライアント側の
		/*
		boolean lflag = false;
		int ltexture = dataWatcher.getWatchableObjectInt(dataWatch_Texture);
		int larmor = (ltexture >>> 16) & 0xffff;
		ltexture &= 0xffff;
		if (textureData.textureIndex[0] != ltexture) {
			textureData.textureIndex[0] = ltexture;
			lflag = true;
		}
		if (textureData.textureIndex[1] != larmor) {
			textureData.textureIndex[1] = larmor;
			lflag = true;
		}
		if (lflag) {
			MMM_TextureManager.instance.postGetTexturePack(this, textureData.getTextureIndex());
		}
		return lflag;
		*/
		// TODO 移行準備:テクスチャ設定
		if(!checkedTextureUpdate){
			checkedTextureUpdate = true;
			requestRenderParamRecall();
		}
		return false;
	}

	@Override
	public int getColor() {
//		return textureData.getColor();
		return dataWatcher.getWatchableObjectByte(dataWatch_Color);
	}

	@Override
	public void setColor(int index) {
		textureData.setColor(index);
		dataWatcher.updateObject(dataWatch_Color, (byte)index);
	}

	public boolean updateMaidColor() {
		// 同一性のチェック
		if(!isContract()&&isNotColorExistsWild) return false;

		int lc = getColor();
		if (textureData.getColor() != lc) {
			textureData.setColor(lc);
			return true;
		}
		return false;
	}

	/**
	 * 紐の持ち主
	 */
	public void updateGotcha() {
		int lid = dataWatcher.getWatchableObjectInt(dataWatch_Gotcha);
		if (lid == 0) {
			mstatgotcha = null;
			return;
		}
		if (mstatgotcha != null && mstatgotcha.getEntityId() == lid) {
			return;
		}
		for (int li = 0; li < worldObj.loadedEntityList.size(); li++) {
			if (((Entity)worldObj.loadedEntityList.get(li)).getEntityId() == lid) {
				mstatgotcha = (Entity)worldObj.loadedEntityList.get(li);
				break;
			}
		}
	}

	public void setGotcha(int pEntityID) {
		dataWatcher.updateObject(dataWatch_Gotcha, Integer.valueOf(pEntityID));
	}
	public void setGotcha(Entity pEntity) {
		setGotcha(pEntity == null ? 0 : pEntity.getEntityId());
	}


	/**
	 * 弓構えを更新
	 */
	public void updateAimebow() {
		boolean lflag = (maidAvatar != null && getAvatarIF().isUsingItemLittleMaid()) || mstatAimeBow;
		setMaidFlags(lflag, dataWatch_Flags_Aimebow);
	}

	public boolean isAimebow() {
		return (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & dataWatch_Flags_Aimebow) > 0;
	}


	/**
	 * 各種フラグのアップデート
	 */
	public void updateMaidFlagsClient() {
		int li = dataWatcher.getWatchableObjectInt(dataWatch_Flags);
		maidFreedom = (li & dataWatch_Flags_Freedom) > 0;
		maidTracer = (li & dataWatch_Flags_Tracer) > 0;
		maidWait = (li & dataWatch_Flags_Wait) > 0;
		mstatAimeBow = (li & dataWatch_Flags_Aimebow) > 0;
		mstatLookSuger = (li & dataWatch_Flags_LooksSugar) > 0;
		mstatBloodsuck = (li & dataWatch_Flags_Bloodsuck) > 0;
		looksWithInterest = (li & dataWatch_Flags_looksWithInterest) > 0;
		looksWithInterestAXIS = (li & dataWatch_Flags_looksWithInterestAXIS) > 0;
		maidOverDriveTime.updateClient((li & dataWatch_Flags_OverDrive) > 0);
		mstatWorkingCount.updateClient((li & dataWatch_Flags_Working) > 0);
	}

	/**
	 * フラグ群に値をセット。
	 * @param pCheck： 対象値。
	 * @param pFlags： 対象フラグ。
	 */
	public void setMaidFlags(boolean pFlag, int pFlagvalue) {
		int li = dataWatcher.getWatchableObjectInt(dataWatch_Flags);
		li = pFlag ? (li | pFlagvalue) : (li & ~pFlagvalue);
		dataWatcher.updateObject(dataWatch_Flags, Integer.valueOf(li));
	}

	/**
	 * 指定されたフラグを獲得
	 */
	public boolean getMaidFlags(int pFlagvalue) {
		return (dataWatcher.getWatchableObjectInt(dataWatch_Flags) & pFlagvalue) > 0;
	}

	/**
	 *  利き腕の設定
	 */
	public void setDominantArm(int pindex) {
		if (mstatSwingStatus.length <= pindex) return;
		if (maidDominantArm == pindex) return;
		for (LMM_SwingStatus lss : mstatSwingStatus) {
			lss.index = lss.lastIndex = -1;
		}
		maidDominantArm = pindex;
		dataWatcher.updateObject(dataWatch_DominamtArm, (byte)maidDominantArm);
		LMM_LittleMaidMobNX.Debug("Change Dominant.");
	}

	@Override
	public void func_175449_a(BlockPos par1, int par4) {
		homeWorld = dimension;
		super.func_175449_a(par1, par4);
	}

	@Override
	public void setTexturePackIndex(int pColor, int[] pIndex) {
		// Server
		LMM_LittleMaidMobNX.Debug("STPI %s", worldObj.isRemote?"Client":"Server");
		textureData.setTexturePackIndex(pColor, pIndex);
		dataWatcher.updateObject(dataWatch_Texture, ((textureData.textureIndex[0] & 0xffff) | (textureData.textureIndex[1] & 0xffff) << 16));
		LMM_LittleMaidMobNX.Debug("changeSize-ID:%d: %f, %f, %b", getEntityId(), width, height, worldObj.isRemote);
		setColor(pColor);
		setTextureNames();
	}

	@Override
	public void setTexturePackName(MMM_TextureBox[] pTextureBox) {
		// Client
		textureData.setTexturePackName(pTextureBox);
		setTextureNames();
		LMM_LittleMaidMobNX.Debug("ID:%d, TextureModel:%s", getEntityId(), textureData.getTextureName(0));
		// モデルの初期化
		((MMM_TextureBox)textureData.textureBox[0]).models[0].setCapsValue(IModelCaps.caps_changeModel, maidCaps);
		// スタビの付け替え
//		for (Entry<String, MMM_EquippedStabilizer> le : pEntity.maidStabilizer.entrySet()) {
//			if (le.getValue() != null) {
//				le.getValue().updateEquippedPoint(pEntity.textureModel0);
//			}
//		}
		maidSoundRate = LMM_SoundManager.instance.getSoundRate(textureData.getTextureName(0), getColor());

	}

	/**
	 * Client用
	 */
	public void setTextureNames() {
		textureData.setTextureNames();
	}

	public void setNextTexturePackege(int pTargetTexture) {
		textureData.setNextTexturePackege(pTargetTexture);
	}

	public void setPrevTexturePackege(int pTargetTexture) {
		textureData.setPrevTexturePackege(pTargetTexture);
	}


	// textureEntity

	@Override
	public void setTextureBox(MMM_TextureBoxBase[] pTextureBox) {
		textureData.setTextureBox(pTextureBox);
	}

	@Override
	public MMM_TextureBoxBase[] getTextureBox() {
		return textureData.getTextureBox();
	}

	@Override
	public void setTextureIndex(int[] pTextureIndex) {
		textureData.setTextureIndex(pTextureIndex);
	}

	@Override
	public int[] getTextureIndex() {
		return textureData.getTextureIndex();
	}

	@Override
	public void setTextures(int pIndex, ResourceLocation[] pNames) {
		textureData.setTextures(pIndex, pNames);
	}

	@Override
	public ResourceLocation[] getTextures(int pIndex) {
		ResourceLocation[] r = textureData.getTextures(pIndex);
		return r;
	}

	@Override
	public MMM_TextureData getTextureData() {
		return textureData;
	}

	// Tile関係

	/**
	 * 使っているTileかどうか判定して返す。
	 */
	public boolean isUsingTile(TileEntity pTile) {
		if (isActiveModeClass()) {
			return getActiveModeClass().isUsingTile(pTile);
		}
		for (int li = 0; li < maidTiles.length; li++) {
			if (maidTiles[li] != null &&
					pTile.getPos().getX() == maidTiles[li][0] &&
					pTile.getPos().getY() == maidTiles[li][1] &&
					pTile.getPos().getZ() == maidTiles[li][2]) {
				return true;
			}
		}
		return false;
	}

	public boolean isEqualTile() {
		return worldObj.getTileEntity(new BlockPos(maidTile[0], maidTile[1], maidTile[2])) == maidTileEntity;
	}

	public boolean isTilePos() {
		return maidTileEntity != null;
	}
	public boolean isTilePos(int pIndex) {
		if (pIndex < maidTiles.length) {
			return maidTiles[pIndex] != null;
		}
		return false;
	}

	/**
	 * ローカル変数にTileの位置を入れる。
	 */
	public boolean getTilePos(int pIndex) {
		if (pIndex < maidTiles.length && maidTiles[pIndex] != null) {
			maidTile[0] = maidTiles[pIndex][0];
			maidTile[1] = maidTiles[pIndex][1];
			maidTile[2] = maidTiles[pIndex][2];
			return true;
		}
		return false;
	}

	public void setTilePos(int pX, int pY, int pZ) {
		maidTile[0] = pX;
		maidTile[1] = pY;
		maidTile[2] = pZ;
	}
	public void setTilePos(TileEntity pEntity) {
		maidTile[0] = pEntity.getPos().getX();
		maidTile[1] = pEntity.getPos().getY();
		maidTile[2] = pEntity.getPos().getZ();
		maidTileEntity = pEntity;
	}
	public void setTilePos(int pIndex) {
		if (pIndex < maidTiles.length) {
			if (maidTiles[pIndex] == null) {
				maidTiles[pIndex] = new int[3];
			}
			maidTiles[pIndex][0] = maidTile[0];
			maidTiles[pIndex][1] = maidTile[1];
			maidTiles[pIndex][2] = maidTile[2];
		}
	}
	public void setTilePos(int pIndex, int pX, int pY, int pZ) {
		if (pIndex < maidTiles.length) {
			if (maidTiles[pIndex] == null) {
				maidTiles[pIndex] = new int[3];
			}
			maidTiles[pIndex][0] = pX;
			maidTiles[pIndex][1] = pY;
			maidTiles[pIndex][2] = pZ;
		}
	}

	public TileEntity getTileEntity() {
		return maidTileEntity = worldObj.getTileEntity(new BlockPos(maidTile[0], maidTile[1], maidTile[2]));
	}
	public TileEntity getTileEntity(int pIndex) {
		if (pIndex < maidTiles.length && maidTiles[pIndex] != null) {
			TileEntity ltile = worldObj.getTileEntity(new BlockPos(
					maidTiles[pIndex][0], maidTiles[pIndex][1], maidTiles[pIndex][2]));
			if (ltile == null) {
				clearTilePos(pIndex);
			}
			return ltile;
		}
		return null;
	}

	public void clearTilePos() {
		maidTileEntity = null;
	}
	public void clearTilePos(int pIndex) {
		if (pIndex < maidTiles.length) {
			maidTiles[pIndex] = null;
		}
	}
	public void clearTilePosAll() {
		for (int li = 0; li < maidTiles.length; li++) {
			maidTiles[li] = null;
		}
	}

	public double getDistanceTilePos() {
		return getDistance(
				maidTile[0] + 0.5D,
				maidTile[1] + 0.5D,
				maidTile[2] + 0.5D);
	}
	public double getDistanceTilePosSq() {
		return getDistanceSq(
				maidTile[0] + 0.5D,
				maidTile[1] + 0.5D,
				maidTile[2] + 0.5D);
	}

	public double getDistanceTilePos(int pIndex) {
		if (maidTiles.length > pIndex && maidTiles[pIndex] != null) {
			return getDistance(
					maidTiles[pIndex][0] + 0.5D,
					maidTiles[pIndex][1] + 0.5D,
					maidTiles[pIndex][2] + 0.5D);
		}
		return -1D;
	}
	public double getDistanceTilePosSq(int pIndex) {
		if (maidTiles.length > pIndex && maidTiles[pIndex] != null) {
			return getDistanceSq(
					maidTiles[pIndex][0] + 0.5D,
					maidTiles[pIndex][1] + 0.5D,
					maidTiles[pIndex][2] + 0.5D);
		}
		return -1D;
	}
	public double getDistanceTilePos(TileEntity pTile) {
		if (pTile != null) {
			return getDistance(
					pTile.getPos().getX() + 0.5D,
					pTile.getPos().getY() + 0.5D,
					pTile.getPos().getZ() + 0.5D);
		}
		return -1D;
	}
	public double getDistanceTilePosSq(TileEntity pTile) {
		if (pTile != null) {
			return getDistanceSq(
					pTile.getPos().getX() + 0.5D,
					pTile.getPos().getY() + 0.5D,
					pTile.getPos().getZ() + 0.5D);
		}
		return -1D;
	}

	public void looksTilePos() {
		getLookHelper().setLookPosition(
				maidTile[0] + 0.5D, maidTile[1] + 0.5D, maidTile[2] + 0.5D,
				10F, getVerticalFaceSpeed());
	}
	public void looksTilePos(int pIndex) {
		if (maidTiles.length > pIndex && maidTiles[pIndex] != null) {
			getLookHelper().setLookPosition(
					maidTiles[pIndex][0] + 0.5D,
					maidTiles[pIndex][1] + 0.5D,
					maidTiles[pIndex][2] + 0.5D,
					10F, getVerticalFaceSpeed());
		}
	}

	public boolean isUsingItem() {
		return dataWatcher.getWatchableObjectInt(dataWatch_ItemUse) > 0;
	}

	public boolean isUsingItem(int pIndex) {
		return (dataWatcher.getWatchableObjectInt(dataWatch_ItemUse) & (1 << pIndex)) > 0;
	}

	public void setExperienceValue(int val)
	{
		this.experienceValue = val;
	}

	@Override
	public void setFlag(int par1, boolean par2) {
		super.setFlag(par1, par2);
	}

	//1.8検討
	/*
	public void updateWanderPath()
	{
		super.updateWanderPath();
	}
	*/

	public void setSize2(float par1, float par2)
	{
		super.setSize(par1, par2);
	}
}
