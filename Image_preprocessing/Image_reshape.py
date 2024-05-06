# 터미널에 pip install opencv-python
import os
import cv2
import numpy as np

mydir='./wardrobe/' # 원하는 파일 이름으로 수정하기!!
index = 1

dir_list = os.listdir(mydir)
# img_list = []
for i in dir_list:
    path = mydir + i

    img = cv2.imread(path, cv2.IMREAD_COLOR)

    img_array = np.array(img)
    img_resize = cv2.resize(img_array, (224,224), interpolation = cv2.INTER_AREA)

    # 수정 확인용 이미지 창
    # cv2.imshow(str(k) + '.png', img_resize)
    # cv2.waitKey(0)
    # cv2.destroyAllWindows()
    
    # 수정 이미지 크기 확인
    # h,w,c = img_resize.shape
    # print('width: ', w)
    # print('height: ', h)

    cv2.imwrite('C:/Users/ohje7/Desktop/resized/wardrobe_resized/' + str(index) +'.png', img_resize)
    index = index + 1