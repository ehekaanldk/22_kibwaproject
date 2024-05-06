#pip install opencv-python으로 opencv 라이브러리 설치

#이미지 대칭
#좌우대칭
import os
import cv2
keyword = 'microwave' #해당 물품 이름 변경
path = 'C:/selenium/data/' + keyword #읽을 파일 경로
os.chdir(path) #해당 폴더로 이동
files = os.listdir(path) #해당 폴더에 있는 파일을 리스트 형태로 받는다.
k = 1
for file in files:
#cv2.imread()는 파일명,이미지를 읽을 flags값 설정
#cv2.IMREAD_COLOR는 기본갑으로 이미지를 3채널 BGR형태로 읽어온다.
    img = cv2.imread(file,cv2.IMREAD_COLOR) 
    
    height, width, channel = img.shape

   
#대칭된 이미지를 저장하는 변수 = 대칭시키기 위한 함수
#filpcode > 0 : 좌우 대칭 수평기준
    flip_horizontal = cv2.flip(img, 1)
    cv2.imwrite('C:/selenium/data/' + keyword + str(k) +'.png', flip_horizontal)
    k = k+1

#오른쪽으로 30도 회전
    matrix = cv2.getRotationMatrix2D((width / 2, height / 2), 30, 1) 
    rotation_right = cv2.warpAffine(img, matrix, (width, height))
    cv2.imwrite('C:/selenium/data/' + keyword + str(k) +'.png', rotation_right)
    k = k + 1

#왼쪽으로 30도 회전
    matrix = cv2.getRotationMatrix2D((width / 2, height / 2), -30, 1) 
    rotation_left = cv2.warpAffine(img, matrix, (width, height))
    cv2.imwrite('C:/selenium/data/' + keyword + str(k) +'.png', rotation_left)
    k = k + 1
    
    
# 수정 확인용 이미지 창
# cv2.imshow(str(k) + '.png', flip_horizontal)
# cv2.waitKey(0)
# cv2.destroyAllWindows()
