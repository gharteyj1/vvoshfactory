Feature: Images

  @images
  Scenario Outline: I upload media to Remember
    Given I want to upload media
    And I add an image with name "<value>"
    When I upload media to Remember
    Then the image is uploaded successfully
    Examples:
      | value			    |
      | square_png.png	    |
      | square_jpg.jpg	    |
      | square_gif.gif		|
      | square_anim_gif.gif	|
      | portrait_png.png	|
      | portrait_jpg.jpg	|
      | portrait_gif.gif	|
      | portrait_anim_gif.gif|
      | landscape_png.png	|
      | landscape_jpg.jpg	|
      | landscape_gif.gif	|
      | landscape_anim_gif.gif|

  @images
  Scenario Outline: I add an image to a post
    Given I want to upload media
    And I add an image with name "<value>"
    When I create the post
    Then my post with image is created successfully
    Examples:
      | value			    |
      | square_png.png	    |
      | square_jpg.jpg	    |
      | square_gif.gif		|
      | square_anim_gif.gif	|
      | portrait_png.png	|
      | portrait_jpg.jpg	|
      | portrait_gif.gif	|
      | portrait_anim_gif.gif|
      | landscape_png.png	|
      | landscape_jpg.jpg	|
      | landscape_gif.gif	|
      | landscape_anim_gif.gif|

  @images
  Scenario Outline: I add an image to an event
    Given I want to upload media
    And I add an image with name "<value>"
    When I upload the image to the event
    Then the image is added successfully to the event
    Examples:
      | value			    |
      | square_png.png	    |
      | square_jpg.jpg	    |
      | square_gif.gif		|
      | square_anim_gif.gif	|
      | portrait_png.png	|
      | portrait_jpg.jpg	|
      | portrait_gif.gif	|
      | portrait_anim_gif.gif|
      | landscape_png.png	|
      | landscape_jpg.jpg	|
      | landscape_gif.gif	|
      | landscape_anim_gif.gif|


  @images
  Scenario Outline: I remove an event image
    Given I want to upload media
    And I add an image with name "<value>"
    When I upload the image to the event
    And I remove an image from the event
    Then my event is updated successfully
    Examples:
      | value			    |
      | square_png.png	    |
      | square_jpg.jpg	    |
      | square_gif.gif		|
      | square_anim_gif.gif	|

  @images
  Scenario Outline: EXIF orientation is maintained
    Given I want to upload media
    And I add an image with name "<value>"
    When I create the post
    Then my post with image is created successfully
    Examples:
      | value							|
      | Portrait_1_HorizontalNormal.jpg	|
      | Portrait_2_MirrorHorizonal.jpg	|
      | Portrait_3_Rotate180.jpg	    |
      | Portrait_4_MirrorVertical.jpg	|
      | Portrait_5_MirrorHR270CW.jpg	|
      | Portrait_6_Rotate90CW.jpg 	    |
      | Portrait_7_MirrorHR90CW.jpg	    |
      | Portrait_8_Rotate270CW.jpg 	    |
      | Landscape_1_HorizontalNormal.jpg|
      | Landscape_2_MirrorHorizonal.jpg	|
      | Landscape_3_Rotate180.jpg	    |
      | Landscape_4_MirrorVertical.jpg	|
      | Landscape_5_MirrorHR270CW.jpg	|
      | Landscape_6_Rotate90CW.jpg 	    |
      | Landscape_7_MirrorHR90CW.jpg	|
      | Landscape_8_Rotate270CW.jpg 	|

  @images
  Scenario: Max file size
    Given I want to upload media
    When I add an image that is larger than 20MB
    Then I see a message informing me that the image is too big

  @images
  Scenario: Large image dimensions
    Given I want to upload media
    When I add an image that is 10000px x 5000px
    Then the image is uploaded successfully

  @images
  Scenario: Min image dimensions
    Given I want to upload media
    When I add an image that is 2px x 2px
    Then the image is uploaded successfully

  @images
  Scenario Outline: I upload an unsupported type
    Given I want to upload media
    And I add an image with name "<value>"
    Then I see a message informing me that the file type is not supported
    Examples:
      | value           |
      | square_svg.svg	|
      | square_bmp.bmp	|
      | square_tiff.tiff|
      | square_zip.zip	|
      | square_txt.txt	|
      | square_xls.xls	|
      | square_doc.doc	|

# We can't run this test as there's a bug in the applications where corrupted images
# are being treated as videos, thus breaking the test
  @ignore
  Scenario: Upload a corrupt image
    Given I want to upload media
    When I add an image that is corrupted
    Then the image is uploaded successfully

  @images
  Scenario: Upload an image and the name is the file name
    Given I want to upload media
    When I add an image with filename “X.jpg”
    Then the image name is the title

  @images
  Scenario: Upload an image with a characters in the title and make sure it is maintained
    Given I want to upload media
    When I add an image with non-alphanumeric characters in the filename
    Then the image name is the title
