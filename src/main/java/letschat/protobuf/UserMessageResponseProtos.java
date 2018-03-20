// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserMessageResponse.proto

package letschat.protobuf;

public final class UserMessageResponseProtos {
  private UserMessageResponseProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface UserMessageResponseOrBuilder extends
      // @@protoc_insertion_point(interface_extends:letschat.protobuf.UserMessageResponse)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 code = 1;</code>
     */
    boolean hasCode();
    /**
     * <code>required int32 code = 1;</code>
     */
    int getCode();

    /**
     * <code>required string fromuser = 2;</code>
     */
    boolean hasFromuser();
    /**
     * <code>required string fromuser = 2;</code>
     */
    java.lang.String getFromuser();
    /**
     * <code>required string fromuser = 2;</code>
     */
    com.google.protobuf.ByteString
        getFromuserBytes();

    /**
     * <code>required string touser = 3;</code>
     */
    boolean hasTouser();
    /**
     * <code>required string touser = 3;</code>
     */
    java.lang.String getTouser();
    /**
     * <code>required string touser = 3;</code>
     */
    com.google.protobuf.ByteString
        getTouserBytes();

    /**
     * <code>required string message = 4;</code>
     */
    boolean hasMessage();
    /**
     * <code>required string message = 4;</code>
     */
    java.lang.String getMessage();
    /**
     * <code>required string message = 4;</code>
     */
    com.google.protobuf.ByteString
        getMessageBytes();

    /**
     * <code>required int64 time = 5;</code>
     */
    boolean hasTime();
    /**
     * <code>required int64 time = 5;</code>
     */
    long getTime();
  }
  /**
   * Protobuf type {@code letschat.protobuf.UserMessageResponse}
   */
  public  static final class UserMessageResponse extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:letschat.protobuf.UserMessageResponse)
      UserMessageResponseOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use UserMessageResponse.newBuilder() to construct.
    private UserMessageResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private UserMessageResponse() {
      code_ = 0;
      fromuser_ = "";
      touser_ = "";
      message_ = "";
      time_ = 0L;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private UserMessageResponse(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              code_ = input.readInt32();
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              fromuser_ = bs;
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              touser_ = bs;
              break;
            }
            case 34: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000008;
              message_ = bs;
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              time_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return letschat.protobuf.UserMessageResponseProtos.internal_static_letschat_protobuf_UserMessageResponse_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return letschat.protobuf.UserMessageResponseProtos.internal_static_letschat_protobuf_UserMessageResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.class, letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.Builder.class);
    }

    private int bitField0_;
    public static final int CODE_FIELD_NUMBER = 1;
    private int code_;
    /**
     * <code>required int32 code = 1;</code>
     */
    public boolean hasCode() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 code = 1;</code>
     */
    public int getCode() {
      return code_;
    }

    public static final int FROMUSER_FIELD_NUMBER = 2;
    private volatile java.lang.Object fromuser_;
    /**
     * <code>required string fromuser = 2;</code>
     */
    public boolean hasFromuser() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string fromuser = 2;</code>
     */
    public java.lang.String getFromuser() {
      java.lang.Object ref = fromuser_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          fromuser_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string fromuser = 2;</code>
     */
    public com.google.protobuf.ByteString
        getFromuserBytes() {
      java.lang.Object ref = fromuser_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        fromuser_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TOUSER_FIELD_NUMBER = 3;
    private volatile java.lang.Object touser_;
    /**
     * <code>required string touser = 3;</code>
     */
    public boolean hasTouser() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string touser = 3;</code>
     */
    public java.lang.String getTouser() {
      java.lang.Object ref = touser_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          touser_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string touser = 3;</code>
     */
    public com.google.protobuf.ByteString
        getTouserBytes() {
      java.lang.Object ref = touser_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        touser_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MESSAGE_FIELD_NUMBER = 4;
    private volatile java.lang.Object message_;
    /**
     * <code>required string message = 4;</code>
     */
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required string message = 4;</code>
     */
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          message_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string message = 4;</code>
     */
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIME_FIELD_NUMBER = 5;
    private long time_;
    /**
     * <code>required int64 time = 5;</code>
     */
    public boolean hasTime() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int64 time = 5;</code>
     */
    public long getTime() {
      return time_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasCode()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasFromuser()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTouser()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMessage()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTime()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, code_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, fromuser_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, touser_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, message_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt64(5, time_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, code_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, fromuser_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, touser_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, message_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, time_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof letschat.protobuf.UserMessageResponseProtos.UserMessageResponse)) {
        return super.equals(obj);
      }
      letschat.protobuf.UserMessageResponseProtos.UserMessageResponse other = (letschat.protobuf.UserMessageResponseProtos.UserMessageResponse) obj;

      boolean result = true;
      result = result && (hasCode() == other.hasCode());
      if (hasCode()) {
        result = result && (getCode()
            == other.getCode());
      }
      result = result && (hasFromuser() == other.hasFromuser());
      if (hasFromuser()) {
        result = result && getFromuser()
            .equals(other.getFromuser());
      }
      result = result && (hasTouser() == other.hasTouser());
      if (hasTouser()) {
        result = result && getTouser()
            .equals(other.getTouser());
      }
      result = result && (hasMessage() == other.hasMessage());
      if (hasMessage()) {
        result = result && getMessage()
            .equals(other.getMessage());
      }
      result = result && (hasTime() == other.hasTime());
      if (hasTime()) {
        result = result && (getTime()
            == other.getTime());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasCode()) {
        hash = (37 * hash) + CODE_FIELD_NUMBER;
        hash = (53 * hash) + getCode();
      }
      if (hasFromuser()) {
        hash = (37 * hash) + FROMUSER_FIELD_NUMBER;
        hash = (53 * hash) + getFromuser().hashCode();
      }
      if (hasTouser()) {
        hash = (37 * hash) + TOUSER_FIELD_NUMBER;
        hash = (53 * hash) + getTouser().hashCode();
      }
      if (hasMessage()) {
        hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
        hash = (53 * hash) + getMessage().hashCode();
      }
      if (hasTime()) {
        hash = (37 * hash) + TIME_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getTime());
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(letschat.protobuf.UserMessageResponseProtos.UserMessageResponse prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code letschat.protobuf.UserMessageResponse}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:letschat.protobuf.UserMessageResponse)
        letschat.protobuf.UserMessageResponseProtos.UserMessageResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return letschat.protobuf.UserMessageResponseProtos.internal_static_letschat_protobuf_UserMessageResponse_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return letschat.protobuf.UserMessageResponseProtos.internal_static_letschat_protobuf_UserMessageResponse_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.class, letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.Builder.class);
      }

      // Construct using letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        code_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        fromuser_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        touser_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        message_ = "";
        bitField0_ = (bitField0_ & ~0x00000008);
        time_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return letschat.protobuf.UserMessageResponseProtos.internal_static_letschat_protobuf_UserMessageResponse_descriptor;
      }

      public letschat.protobuf.UserMessageResponseProtos.UserMessageResponse getDefaultInstanceForType() {
        return letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.getDefaultInstance();
      }

      public letschat.protobuf.UserMessageResponseProtos.UserMessageResponse build() {
        letschat.protobuf.UserMessageResponseProtos.UserMessageResponse result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public letschat.protobuf.UserMessageResponseProtos.UserMessageResponse buildPartial() {
        letschat.protobuf.UserMessageResponseProtos.UserMessageResponse result = new letschat.protobuf.UserMessageResponseProtos.UserMessageResponse(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.code_ = code_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.fromuser_ = fromuser_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.touser_ = touser_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.message_ = message_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.time_ = time_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof letschat.protobuf.UserMessageResponseProtos.UserMessageResponse) {
          return mergeFrom((letschat.protobuf.UserMessageResponseProtos.UserMessageResponse)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(letschat.protobuf.UserMessageResponseProtos.UserMessageResponse other) {
        if (other == letschat.protobuf.UserMessageResponseProtos.UserMessageResponse.getDefaultInstance()) return this;
        if (other.hasCode()) {
          setCode(other.getCode());
        }
        if (other.hasFromuser()) {
          bitField0_ |= 0x00000002;
          fromuser_ = other.fromuser_;
          onChanged();
        }
        if (other.hasTouser()) {
          bitField0_ |= 0x00000004;
          touser_ = other.touser_;
          onChanged();
        }
        if (other.hasMessage()) {
          bitField0_ |= 0x00000008;
          message_ = other.message_;
          onChanged();
        }
        if (other.hasTime()) {
          setTime(other.getTime());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCode()) {
          return false;
        }
        if (!hasFromuser()) {
          return false;
        }
        if (!hasTouser()) {
          return false;
        }
        if (!hasMessage()) {
          return false;
        }
        if (!hasTime()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        letschat.protobuf.UserMessageResponseProtos.UserMessageResponse parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (letschat.protobuf.UserMessageResponseProtos.UserMessageResponse) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int code_ ;
      /**
       * <code>required int32 code = 1;</code>
       */
      public boolean hasCode() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 code = 1;</code>
       */
      public int getCode() {
        return code_;
      }
      /**
       * <code>required int32 code = 1;</code>
       */
      public Builder setCode(int value) {
        bitField0_ |= 0x00000001;
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 code = 1;</code>
       */
      public Builder clearCode() {
        bitField0_ = (bitField0_ & ~0x00000001);
        code_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object fromuser_ = "";
      /**
       * <code>required string fromuser = 2;</code>
       */
      public boolean hasFromuser() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required string fromuser = 2;</code>
       */
      public java.lang.String getFromuser() {
        java.lang.Object ref = fromuser_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            fromuser_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string fromuser = 2;</code>
       */
      public com.google.protobuf.ByteString
          getFromuserBytes() {
        java.lang.Object ref = fromuser_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          fromuser_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string fromuser = 2;</code>
       */
      public Builder setFromuser(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        fromuser_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string fromuser = 2;</code>
       */
      public Builder clearFromuser() {
        bitField0_ = (bitField0_ & ~0x00000002);
        fromuser_ = getDefaultInstance().getFromuser();
        onChanged();
        return this;
      }
      /**
       * <code>required string fromuser = 2;</code>
       */
      public Builder setFromuserBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        fromuser_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object touser_ = "";
      /**
       * <code>required string touser = 3;</code>
       */
      public boolean hasTouser() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string touser = 3;</code>
       */
      public java.lang.String getTouser() {
        java.lang.Object ref = touser_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            touser_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string touser = 3;</code>
       */
      public com.google.protobuf.ByteString
          getTouserBytes() {
        java.lang.Object ref = touser_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          touser_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string touser = 3;</code>
       */
      public Builder setTouser(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        touser_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string touser = 3;</code>
       */
      public Builder clearTouser() {
        bitField0_ = (bitField0_ & ~0x00000004);
        touser_ = getDefaultInstance().getTouser();
        onChanged();
        return this;
      }
      /**
       * <code>required string touser = 3;</code>
       */
      public Builder setTouserBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        touser_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object message_ = "";
      /**
       * <code>required string message = 4;</code>
       */
      public boolean hasMessage() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required string message = 4;</code>
       */
      public java.lang.String getMessage() {
        java.lang.Object ref = message_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            message_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string message = 4;</code>
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        java.lang.Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string message = 4;</code>
       */
      public Builder setMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string message = 4;</code>
       */
      public Builder clearMessage() {
        bitField0_ = (bitField0_ & ~0x00000008);
        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <code>required string message = 4;</code>
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000008;
        message_ = value;
        onChanged();
        return this;
      }

      private long time_ ;
      /**
       * <code>required int64 time = 5;</code>
       */
      public boolean hasTime() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required int64 time = 5;</code>
       */
      public long getTime() {
        return time_;
      }
      /**
       * <code>required int64 time = 5;</code>
       */
      public Builder setTime(long value) {
        bitField0_ |= 0x00000010;
        time_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int64 time = 5;</code>
       */
      public Builder clearTime() {
        bitField0_ = (bitField0_ & ~0x00000010);
        time_ = 0L;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:letschat.protobuf.UserMessageResponse)
    }

    // @@protoc_insertion_point(class_scope:letschat.protobuf.UserMessageResponse)
    private static final letschat.protobuf.UserMessageResponseProtos.UserMessageResponse DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new letschat.protobuf.UserMessageResponseProtos.UserMessageResponse();
    }

    public static letschat.protobuf.UserMessageResponseProtos.UserMessageResponse getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<UserMessageResponse>
        PARSER = new com.google.protobuf.AbstractParser<UserMessageResponse>() {
      public UserMessageResponse parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new UserMessageResponse(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<UserMessageResponse> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserMessageResponse> getParserForType() {
      return PARSER;
    }

    public letschat.protobuf.UserMessageResponseProtos.UserMessageResponse getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_letschat_protobuf_UserMessageResponse_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_letschat_protobuf_UserMessageResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031UserMessageResponse.proto\022\021letschat.pr" +
      "otobuf\"d\n\023UserMessageResponse\022\014\n\004code\030\001 " +
      "\002(\005\022\020\n\010fromuser\030\002 \002(\t\022\016\n\006touser\030\003 \002(\t\022\017\n" +
      "\007message\030\004 \002(\t\022\014\n\004time\030\005 \002(\003B.\n\021letschat" +
      ".protobufB\031UserMessageResponseProtos"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_letschat_protobuf_UserMessageResponse_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_letschat_protobuf_UserMessageResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_letschat_protobuf_UserMessageResponse_descriptor,
        new java.lang.String[] { "Code", "Fromuser", "Touser", "Message", "Time", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}