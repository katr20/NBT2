package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTInteger extends NBTBase {
    private Integer tagValue;

    public NBTInteger(String key) {
	super(key);
    }

    public NBTInteger(String key, Integer value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    return new NBTInteger(key, input.readInt());
	}
	return new NBTString(key, null);
    }

    public void setValue(Integer i) {
	this.tagValue = i;
    }

    public Integer getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue!=null) {
	    output.writeByte(0);
	    output.writeInt(this.tagValue);
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public byte getTagType() {
	return NBTBase.INTEGER_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTInteger(getKey(), this.tagValue);
    }

}
