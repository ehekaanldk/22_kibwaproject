from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import urllib.request

# 변경사항 설정
item_name = "에어프라이" ## 검색어
img_folder = "air fryer" ## 저장할 폴더 이름
count = 182 ## 사진 번호

# 드라이버 설정
options = webdriver.ChromeOptions()
options.add_experimental_option("excludeSwitches", ["enable-logging"])
driver = webdriver.Chrome(options=options)
driver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&ogbl")

# 검색어 입력창 지정 및 검색
elem = driver.find_element_by_name("q")
elem.send_keys("중고 " + item_name)
elem.send_keys(Keys.RETURN)

# 스크롤 내리기
SCROLL_PAUSE_TIME = 1
# Get scroll height
last_height = driver.execute_script("return document.body.scrollHeight")

while True:
    # Scroll down to bottom
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
    # Wait to load page
    time.sleep(SCROLL_PAUSE_TIME)
    # Calculate new scroll height and compare with last scroll height
    new_height = driver.execute_script("return document.body.scrollHeight")
    if new_height == last_height:
        try:
            driver.find_element_by_css_selector(".mye4qd").click()
        except:
            break
    last_height = new_height

# 이미지 파일 받아오기
images = driver.find_elements_by_css_selector(".rg_i.Q4LuWd")

# 로컬 파일로 저장
for image in images:
    try:
        image.click()
        time.sleep(2)
        imgUrl = driver.find_element_by_xpath("/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[3]/div/a/img").get_attribute("src")
        #urllib.request.urlretrieve(imgUrl, f"./F_Images/{img_folder}/{img_folder}{count}.jpg")
        urllib.request.urlretrieve(imgUrl, f"./E_Images/{img_folder}/{img_folder}{count}.jpg")
        time.sleep(1)
        count = count + 1
    except:
        pass

#driver.close()