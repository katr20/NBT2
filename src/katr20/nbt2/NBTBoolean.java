package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTBoolean extends NBTBase {
    private Boolean tagValue;

    public NBTBoolean(String key) {
	super(key);
    }

    public NBTBoolean(String key, Boolean value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    return new NBTBoolean(key, input.readBoolean());
	}
	return new NBTBoolean(key, null);
    }

    public void setValue(Boolean b) {
	this.tagValue = b;
    }

    @Override
    public Boolean getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue != null) {
	    output.writeByte(0);
	    output.writeBoolean(this.tagValue);
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public NBTTagType getTagType() {
	return NBTTagType.BOOLEAN_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTBoolean(getKey(), this.tagValue);
    }

}
