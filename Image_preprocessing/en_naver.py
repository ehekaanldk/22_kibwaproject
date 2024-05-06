#1. 필요한 패키지를 임포트하기
from selenium import webdriver #크롬 웹브라우져를 자동으로 제어하기 위해
from selenium.webdriver.common.keys import Keys #키보드를 컴퓨터가 알아서 누르게 하기 위해
                                                #필요한 모듈
from bs4 import BeautifulSoup
import urllib.request
import time

#2. 크롬 드라이버의 위치 지정 후 driver객체 생성
driver = webdriver.Chrome("c:\chromedriver.exe")

#3. 빙의 이미지 검색 url을 받아옵니다.(키워드를 아무것도 안쳤을때의 url)
driver.get("https://search.naver.com/search.naver?where=image&sm=tab_jum&query=")

#4. 검색창에 검색 키워드를 넣기 위해서는 웹페이지의 검색창의 클래스 이름을 찾아서 
#검색창에 해당하는 부분이 어디다라고 알려주는 객체가 필요합니다.
search = driver.find_element_by_id('nx_query') #id로 검색시 

#5. 검색어를 자동으로 입력하게 합니다.
search.send_keys(input('검색어를 입력하세요'))

#6.엔터를 자동으로 치게 합니다.
search.submit()

#7. 검색한 이미지를 다 볼 수 있게 END키를 눌러서 스크롤을 아래로 계속 내릴 수 있게 합니다.
for i in range(1,15):
    driver.find_element_by_xpath("//body").send_keys(Keys.END) #키보드 END키를 누른다
    time.sleep(10)
    
#8. 지금 현재 보이는 웹페이지의 html코드를 저장하기
time.sleep(10)  #네트워크 느릴까봐 안정성을 위해 sleep해줌
html=driver.page_source #크롬 브라우져에서 현재 불러온 소스를 가져옴
soup=BeautifulSoup(html,"lxml") #html코드를 뷰티풀 스프 모듈로 파싱합니다.

#9. 이미지에 대한 상세url을 params리스트에 담는 함수 생성
def fetch_list_url():

    params=[]
    imgList=soup.find_all("img",class_="_image _listImage")
    for i in imgList:
        params.append(i.get("data-lazy-src",i.get("src")))

    return params

#10.상세 url의 이미지들을 우리 컴퓨터에 다운로드 하는 함수 생성

def fetch_detail_url():
      

    params2=fetch_list_url()
    
    for idx, p in enumerate(params2,4551): #idx를 1번 부터 시작해라~
        urllib.request.urlretrieve(p,"c:\\selenium\\air fryer"+str(idx) +".jpg") #상세 url넣어주면 다운로드됨

fetch_detail_url()

#11. 끝났으면 브라우져 닫기
driver.quit()
