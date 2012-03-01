package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTString extends NBTBase {
    private String tagValue;

    public NBTString(String key) {
	super(key);
    }

    public NBTString(String key, String value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    return new NBTString(key, input.readUTF());
	}
	return new NBTString(key, null);
    }

    public void setValue(String str) {
	this.tagValue = str;
    }

    @Override
    public String getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue!=null) {
	    output.writeByte(0);
	    output.writeUTF(this.tagValue);
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public byte getTagType() {
	return NBTBase.STRING_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTString(getKey(), this.tagValue);
    }

}
