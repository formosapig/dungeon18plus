package com.qqhouse.dungeon18plus.struct;

import com.qqhouse.dungeon18plus.core.Item;

public class EquipmentMastery implements Comparable<EquipmentMastery>/*, Parcelable*/ {
	public final Item equipment;
	public int mastery;
	
	public EquipmentMastery(Item equip, int mastery) {
		this.equipment = equip;
		this.mastery = mastery;
	}
	
	/*
	 * Comparable
	 */
	@Override
	public int compareTo(EquipmentMastery another) {
		return equipment.compareTo(another.equipment);
	}
	
	/*
	 * Parcelable
	 */
	/*
	private EquipmentMastery(Parcel in) {
        this.equipment = Item.find(in.readInt());
        this.mastery = in.readInt();
    }
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.equipment.code);
		dest.writeInt(this.mastery);
	}

    public static final Parcelable.Creator<EquipmentMastery> CREATOR
            = new Parcelable.Creator<EquipmentMastery>() {

        public EquipmentMastery createFromParcel(Parcel in) {
            return new EquipmentMastery(in);
        }

        public EquipmentMastery[] newArray(int size) {
            return new EquipmentMastery[size];
        }
    };*/
}
