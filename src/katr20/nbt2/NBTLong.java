package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTLong extends NBTBase {
    private Long tagValue;

    public NBTLong(String key) {
	super(key);
    }

    public NBTLong(String key, Long value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    return new NBTLong(key, input.readLong());
	}
	return new NBTString(key, null);
    }

    public void setValue(Long l) {
	this.tagValue = l;
    }

    @Override
    public Long getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue != null) {
	    output.writeByte(0);
	    output.writeLong(this.tagValue);
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public NBTTagType getTagType() {
	return NBTTagType.LONG_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTLong(getKey(), this.tagValue);
    }

}
