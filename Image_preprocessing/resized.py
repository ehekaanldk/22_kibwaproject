from PIL import Image
import os
import glob
import cv2
import numpy as np
from matplotlib.pyplot import imshow
keyword = 'microwave'
file_path = 'C:/selenium/data/'+ keyword #읽을 파일 경로
#file_names = os.chdir(file_path) #해당 폴더로 이동
file_names = os.listdir(file_path) #해당 폴더에 있는 파일을 리스트 형태로 받는다.
i = 1

for name in file_names:
    src = os.path.join(file_path, name)
    dst = str(i) + 'resized' + '.png'
    dst = os.path.join(file_path, dst)
    os.rename(src, dst)
    i += 1

imglist = glob.glob('C:/selenium/data/microwave/*.jpg')
for name in imglist:
  if not os.path.isdir(name):
        src = os.path.splitext(name)
        os.rename(name,src[0]+'.png')

# 크기 사이즈 변경하기 위해서 불러들임, 사이즈 변경된거 저장 

original_path = 'C:/selenium/data/'+ keyword
resized_path = 'C:/selenium/data/'+ keyword
file_list = os.listdir(original_path)
img_list = []

for item in file_list :
    if item.find('.png') is not -1 :
        img_list.append(item)

total_image = len(img_list)
index = 0

# 사이즈 변경
for name in img_list :

    img = Image.open('%s/%s'%(original_path, name))
    img_array = np.array(img)
    img_resize = cv2.resize(img_array, (224,224), interpolation = cv2.INTER_AREA)
    img = Image.fromarray(img_resize)
    img.save('%s%s' % (resized_path, name))

    print(name + '   ' + str(index) + '/' + str(total_image))
    index = index + 1


# 사이즈 변경 확인
#img = cv2.imread('/content/drive/MyDrive/resized_images/resized_bed/1_resized.png')

#h,w,c = img.shape

#print('width: ',w)
#print('height: ',h)


#for file in files:
#cv2.imread()는 파일명,이미지를 읽을 flags값 설정
#cv2.IMREAD_COLOR는 기본갑으로 이미지를 3채널 BGR형태로 읽어온다.
 #   img = cv2.imread(file,cv2.IMREAD_COLOR) 
 #   img_resize = cv2.resize(img,(224,224),interpolation = cv2.INTER_AREA)
 #   cv2.imwrite(path + str(k) +'.png', img_resize)
 #   k=k+1

#폴더, 파일 지우기

#import shutil
#shutil.rmtree('C:/selenium/data/air fryer/')
