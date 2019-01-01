package bert.rotmg.model;

import java.io.DataInput;
import java.io.IOException;
import java.util.Arrays;

import bert.rotmg.constants.StatType;

/**
 * Object status data that is received from server to render game objects
 * 
 * @author Bert
 */
public class ObjectStatusData {

	public static PlayerData processObject(ObjectData od) {
		PlayerData playerData = processObjectStatus(od.status);
		playerData.classType = od.objectType;
		return playerData;
	}
	
	public static PlayerData processObjectStatus(ObjectStatusData osd) {
		PlayerData playerData = processStatData(osd.stats, new PlayerData());
		playerData.position = osd.pos;
		playerData.objectId = osd.objectId;
		return playerData;
	}

	public static PlayerData processStatData(StatData[] statData, PlayerData playerData) {
		if (playerData.inventory == null)
			Arrays.fill(playerData.inventory = new int[20], -1);
		for (StatData stat : statData) {
			switch (stat.statType) {
			case StatType.NAME_STAT:
				playerData.name = stat.stringStatValue;
				continue;
			case StatType.LEVEL_STAT:
				playerData.level = stat.statValue;
				continue;
			case StatType.EXP_STAT:
				playerData.exp = stat.statValue;
				continue;
			case StatType.CURR_FAME_STAT:
				playerData.currentFame = stat.statValue;
				continue;
			case StatType.NUM_STARS_STAT:
				playerData.stars = stat.statValue;
				continue;
			case StatType.ACCOUNT_ID_STAT:
				playerData.accountId = stat.stringStatValue;
				continue;
			case StatType.FAME_STAT:
				playerData.accountFame = stat.statValue;
				continue;
			case StatType.CREDITS_STAT:
				playerData.gold = stat.statValue;
				continue;
			case StatType.MAX_HP_STAT:
				playerData.maxHP = stat.statValue;
				continue;
			case StatType.MAX_MP_STAT:
				playerData.maxMP = stat.statValue;
				continue;
			case StatType.HP_STAT:
				playerData.hp = stat.statValue;
				continue;
			case StatType.MP_STAT:
				playerData.mp = stat.statValue;
				continue;
			case StatType.ATTACK_STAT:
				playerData.attack = stat.statValue;
				continue;
			case StatType.DEFENSE_STAT:
				playerData.defense = stat.statValue;
				continue;
			case StatType.SPEED_STAT:
				playerData.speed = stat.statValue;
				continue;
			case StatType.DEXTERITY_STAT:
				playerData.dexterity = stat.statValue;
				continue;
			case StatType.VITALITY_STAT:
				playerData.vitality = stat.statValue;
				continue;
			case StatType.WISDOM_STAT:
				playerData.wisdom = stat.statValue;
				continue;
			case StatType.CONDITION_STAT:
				playerData.condition = stat.statValue;
				continue;
			case StatType.HEALTH_POTION_STACK_STAT:
				playerData.hpPots = stat.statValue;
				continue;
			case StatType.MAGIC_POTION_STACK_STAT:
				playerData.mpPots = stat.statValue;
				continue;
			case StatType.HASBACKPACK_STAT:
				playerData.hasBackpack = stat.statValue == 1;
				continue;
			case StatType.NAME_CHOSEN_STAT:
				playerData.nameChosen = stat.statValue != 0;
				continue;
			case StatType.GUILD_NAME_STAT:
				playerData.guildName = stat.stringStatValue;
				continue;
			case StatType.GUILD_RANK_STAT:
				playerData.guildRank = stat.statValue;
				continue;
			default:
				if (stat.statType >= StatType.INVENTORY_0_STAT && stat.statType <= StatType.INVENTORY_11_STAT) {
					playerData.inventory[stat.statType - 8] = stat.statValue;
				} else if (stat.statType >= StatType.BACKPACK_0_STAT && stat.statType <= StatType.BACKPACK_7_STAT) {
					playerData.inventory[stat.statType - 59] = stat.statValue;
				}
			}
		}
		return playerData;
	}

	public int objectId;

	public WorldPosData pos;

	public StatData[] stats;

	public void read(DataInput in) throws IOException {
		this.objectId = in.readInt();
		this.pos = new WorldPosData();
		this.pos.read(in);
		this.stats = new StatData[in.readShort()];
		for (int i = 0; i < stats.length; i++) {
			(this.stats[i] = new StatData()).read(in);
		}
	}
}
