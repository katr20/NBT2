package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTFloat extends NBTBase {
    private Float tagValue;

    public NBTFloat(String key) {
	super(key);
    }

    public NBTFloat(String key, Float value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    return new NBTFloat(key, input.readFloat());
	}
	return new NBTString(key, null);
    }

    public void setValue(Float f) {
	this.tagValue = f;
    }

    @Override
    public Float getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue != null) {
	    output.writeByte(0);
	    output.writeFloat(this.tagValue);
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public NBTTagType getTagType() {
	return NBTTagType.FLOAT_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTFloat(getKey(), this.tagValue);
    }

}
