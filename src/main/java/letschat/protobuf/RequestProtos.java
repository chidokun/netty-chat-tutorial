// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Request.proto

package letschat.protobuf;

public final class RequestProtos {
  private RequestProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface RequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:letschat.protobuf.Request)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string code = 1;</code>
     */
    boolean hasCode();
    /**
     * <code>required string code = 1;</code>
     */
    java.lang.String getCode();
    /**
     * <code>required string code = 1;</code>
     */
    com.google.protobuf.ByteString
        getCodeBytes();
  }
  /**
   * Protobuf type {@code letschat.protobuf.Request}
   */
  public  static final class Request extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:letschat.protobuf.Request)
      RequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Request.newBuilder() to construct.
    private Request(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Request() {
      code_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Request(
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
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              code_ = bs;
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
      return letschat.protobuf.RequestProtos.internal_static_letschat_protobuf_Request_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return letschat.protobuf.RequestProtos.internal_static_letschat_protobuf_Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              letschat.protobuf.RequestProtos.Request.class, letschat.protobuf.RequestProtos.Request.Builder.class);
    }

    private int bitField0_;
    public static final int CODE_FIELD_NUMBER = 1;
    private volatile java.lang.Object code_;
    /**
     * <code>required string code = 1;</code>
     */
    public boolean hasCode() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string code = 1;</code>
     */
    public java.lang.String getCode() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          code_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string code = 1;</code>
     */
    public com.google.protobuf.ByteString
        getCodeBytes() {
      java.lang.Object ref = code_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        code_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, code_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, code_);
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
      if (!(obj instanceof letschat.protobuf.RequestProtos.Request)) {
        return super.equals(obj);
      }
      letschat.protobuf.RequestProtos.Request other = (letschat.protobuf.RequestProtos.Request) obj;

      boolean result = true;
      result = result && (hasCode() == other.hasCode());
      if (hasCode()) {
        result = result && getCode()
            .equals(other.getCode());
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
        hash = (53 * hash) + getCode().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static letschat.protobuf.RequestProtos.Request parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static letschat.protobuf.RequestProtos.Request parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static letschat.protobuf.RequestProtos.Request parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static letschat.protobuf.RequestProtos.Request parseFrom(
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
    public static Builder newBuilder(letschat.protobuf.RequestProtos.Request prototype) {
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
     * Protobuf type {@code letschat.protobuf.Request}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:letschat.protobuf.Request)
        letschat.protobuf.RequestProtos.RequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return letschat.protobuf.RequestProtos.internal_static_letschat_protobuf_Request_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return letschat.protobuf.RequestProtos.internal_static_letschat_protobuf_Request_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                letschat.protobuf.RequestProtos.Request.class, letschat.protobuf.RequestProtos.Request.Builder.class);
      }

      // Construct using letschat.protobuf.RequestProtos.Request.newBuilder()
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
        code_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return letschat.protobuf.RequestProtos.internal_static_letschat_protobuf_Request_descriptor;
      }

      public letschat.protobuf.RequestProtos.Request getDefaultInstanceForType() {
        return letschat.protobuf.RequestProtos.Request.getDefaultInstance();
      }

      public letschat.protobuf.RequestProtos.Request build() {
        letschat.protobuf.RequestProtos.Request result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public letschat.protobuf.RequestProtos.Request buildPartial() {
        letschat.protobuf.RequestProtos.Request result = new letschat.protobuf.RequestProtos.Request(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.code_ = code_;
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
        if (other instanceof letschat.protobuf.RequestProtos.Request) {
          return mergeFrom((letschat.protobuf.RequestProtos.Request)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(letschat.protobuf.RequestProtos.Request other) {
        if (other == letschat.protobuf.RequestProtos.Request.getDefaultInstance()) return this;
        if (other.hasCode()) {
          bitField0_ |= 0x00000001;
          code_ = other.code_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCode()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        letschat.protobuf.RequestProtos.Request parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (letschat.protobuf.RequestProtos.Request) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object code_ = "";
      /**
       * <code>required string code = 1;</code>
       */
      public boolean hasCode() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string code = 1;</code>
       */
      public java.lang.String getCode() {
        java.lang.Object ref = code_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            code_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string code = 1;</code>
       */
      public com.google.protobuf.ByteString
          getCodeBytes() {
        java.lang.Object ref = code_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          code_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string code = 1;</code>
       */
      public Builder setCode(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        code_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string code = 1;</code>
       */
      public Builder clearCode() {
        bitField0_ = (bitField0_ & ~0x00000001);
        code_ = getDefaultInstance().getCode();
        onChanged();
        return this;
      }
      /**
       * <code>required string code = 1;</code>
       */
      public Builder setCodeBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        code_ = value;
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


      // @@protoc_insertion_point(builder_scope:letschat.protobuf.Request)
    }

    // @@protoc_insertion_point(class_scope:letschat.protobuf.Request)
    private static final letschat.protobuf.RequestProtos.Request DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new letschat.protobuf.RequestProtos.Request();
    }

    public static letschat.protobuf.RequestProtos.Request getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Request>
        PARSER = new com.google.protobuf.AbstractParser<Request>() {
      public Request parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Request(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Request> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Request> getParserForType() {
      return PARSER;
    }

    public letschat.protobuf.RequestProtos.Request getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_letschat_protobuf_Request_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_letschat_protobuf_Request_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rRequest.proto\022\021letschat.protobuf\"\027\n\007Re" +
      "quest\022\014\n\004code\030\001 \002(\tB\"\n\021letschat.protobuf" +
      "B\rRequestProtos"
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
    internal_static_letschat_protobuf_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_letschat_protobuf_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_letschat_protobuf_Request_descriptor,
        new java.lang.String[] { "Code", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
