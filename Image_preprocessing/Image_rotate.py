# 터미널에 pip install opencv-python
import os
import cv2

mydir='./wardrobe/' # 원하는 파일 이름으로 수정하기!!
k = 1

dir_list = os.listdir(mydir)
# print(dir_list[309])
for i in dir_list:
    try:
        path = mydir + i

        img = cv2.imread(path, cv2.IMREAD_COLOR)
        height, width, channel = img.shape

        matrix1 = cv2.getRotationMatrix2D((width / 2, height / 2), 30, 1)
        matrix2 = cv2.getRotationMatrix2D((width / 2, height / 2), -30, 1)

        dst1 = cv2.warpAffine(img, matrix1, (width, height))
        dst2 = cv2.warpAffine(img, matrix2, (width, height))
        flipped = cv2.flip(img, 1)
    
        # 원하는 파일 이름으로 수정하기!!
        cv2.imwrite('C:/Users/ohje7/Desktop/rotate/wardrobe/' + str(k) +'.png', img)
        cv2.imwrite('C:/Users/ohje7/Desktop/rotate/wardrobe/' + str(k+1) +'.png', dst1)
        cv2.imwrite('C:/Users/ohje7/Desktop/rotate/wardrobe/' + str(k+2) +'.png', dst2)
        cv2.imwrite('C:/Users/ohje7/Desktop/rotate/wardrobe/' + str(k+3) +'.png', flipped)
    except:
        continue

    # 수정 확인용 이미지 창
    # cv2.imshow(str(k) + '.png', flipped)
    # cv2.waitKey(0)
    # cv2.destroyAllWindows()
    k = k + 4