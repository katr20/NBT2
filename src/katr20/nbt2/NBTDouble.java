package katr20.nbt2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTDouble extends NBTBase {
    private Double tagValue;

    public NBTDouble(String key) {
	super(key);
    }

    public NBTDouble(String key, Double value) {
	super(key);
	this.tagValue = value;
    }

    public static NBTBase readTag(DataInput input) throws IOException {
	String key = input.readUTF();
	byte isNull = input.readByte();
	if (isNull == 0) {
	    return new NBTDouble(key, input.readDouble());
	}
	return new NBTString(key, null);
    }

    public void setValue(Double d) {
	this.tagValue = d;
    }

    @Override
    public Double getValue() {
	return this.tagValue;
    }

    @Override
    public void writeTagContents(DataOutput output) throws IOException {
	output.writeUTF(getKey());
	if (this.tagValue != null) {
	    output.writeByte(0);
	    output.writeDouble(this.tagValue);
	} else {
	    output.writeByte(1);
	}
    }

    @Override
    public NBTTagType getTagType() {
	return NBTTagType.DOUBLE_TAG;
    }

    @Override
    public NBTBase cloneTag() {
	return new NBTDouble(getKey(), this.tagValue);
    }

}
