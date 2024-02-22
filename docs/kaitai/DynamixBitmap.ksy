meta:
  id: 3space_2_0_dynamix_bitmap
  title: 3Space 2.0 Dynamix Bitmap file
  file-extension: DBM
  endian: le

doc: |
  DBM is used by several Dynamix games, format spans several years

seq:
  - id: header
    type: header
  - id: imageData
    type: chunk
  
types:
  header:
    seq:
      - id: magic
        contents: 0E002800
      - id: fileSize
        type: u4
        doc: |
          Total length of binary after the 4 header bytes.
      - id: height
        type: u2
      - id: width
        type: u2
      - id: bitDepth
        type: u4
      - id: nullSpacer
        type: u2
      - id: imageBytesLength
        type: u4
        doc: |
          total length of specifically the image byte data 
      - id: nullSpacer2
        type: u1
  bin:
    seq:
      - id: data
        size-eos: true