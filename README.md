# 22_kibwaproject
데이터 분석 활용한 대형 폐기품(가전, 가구 등) 간소화 시스템

## 📘 프로젝트 소개

본 프로젝트는 데이터 분석 및 딥러닝을 활용하여 **생활 속 대형 폐기품(가전, 가구 등)의 처리 과정을 간소화**하는  
AI 기반 어플리케이션을 개발하는 것을 목표로 합니다.  
폐기물 자동 분류, 중고 거래 연동, 광고 기반 추천 등 다양한 방식으로 폐기물을 효율적으로 처리할 수 있는 시스템을 구현하였습니다.

![구성도](https://github.com/chaem0-0/22_kibwaproject/assets/96873719/d28982e8-6173-4ab4-a7fe-14e0ed8622a4)


## 🎯 프로젝트 목적

본 시스템은 다음 4가지 핵심 요소를 기반으로 기존의 폐기물 처리 과정을 보완합니다:

1. **편리성**: 처리 과정에서 발생하는 번거로운 절차를 최소화하여 사용자 경험 향상  
2. **정확성**: 분류가 애매한 항목도 AI 기반으로 자동 정확 분류  
3. **확장성**: 중고 거래 서비스 연동을 통해 재활용 구조 확대  
4. **수익성**: 폐기 이후 사용자 맞춤형 신제품 광고 추천을 통한 수익 창출 가능


## 📌 주요 기능

- 대형 폐기물 사진 업로드 → AI 분류 모델을 통해 항목 자동 분류  
- 분류 결과에 따라 수거 신청 또는 중고 거래 서비스 연동  
- 처리 완료 후 목록 확인


## ⚙️ 기술 스택

| 항목 | 사용 기술 |
|------|-----------|
| 언어 | Python, Java |
| 프레임워크 / 라이브러리 | TensorFlow, Keras, Pandas, NumPy |
| 웹 크롤링 | Selenium, BeautifulSoup |
| 딥러닝 모델 | DenseNet121, ResNet152V2, DenseNet201 |
| 개발 환경 | Google Colab, Jupyter Notebook |
| 데이터베이스 | MongoDB, MySQL, AWS RDS |
| 서버 / 인프라 | AWS EC2 |
| 모바일 개발 | Android Studio |



## 🧾 데이터셋

### 수집 방식
- Google, Naver 등 웹사이트를 활용한 이미지 크롤링
- AI Hub의 생활 폐기물 이미지 데이터셋 활용

### 클래스 구성 (총 18종)

#### 가구 (9종)
- Bed, Chair, Desk, Dressing table, Massage chair  
- Shelf, Sofa, Table, Wardrobe

#### 전자제품 (9종)
- Air fryer, Fan, Gas stove, Microwave, Refrigerator  
- Rice cooker, TV, Vacuum, Washing machine


## 🔬 학습 및 추론 방식 (DensNet vs ResNet)
전이학습 기반 CNN 모델 3종을 비교 실험:
- **전이 학습(Transfer Learning)**을 활용하여 소량의 폐기물 이미지 데이터에도 효과적으로 학습할 수 있도록 설계.
- 사전 학습된 ImageNet 가중치를 활용하고, 최상위 레이어를 폐기물 18종 분류에 맞게 재학습(fine-tuning) 함.
- 데이터셋은 훈련(80%), 검증(10%), 테스트(10%)로 분할하여 학습의 일반화 성능 확보.


## 📊 모델 성능 비교
| 모델 | Val_Loss | Val_Accuracy (%) |
|------|----------|------------------|
| **DenseNet121** | **0.0691** | **97.82** |
| ResNet152V2 | 0.1295 | 97.02 |
| DenseNet201 | 0.1579 | 96.05 |

- ✅ **DenseNet121**은 가장 적은 Loss와 높은 Accuracy를 기록하여 최종 분류 모델로 선정  
- ✅ DenseNet은 모든 레이어가 서로 연결되어 있어 적은 정보 손실과 높은 학습 효율
- ✅ ResNet보다 더 적은 파라미터로도 높은 정확도 달성 확인


## 📦 결과물
<p align="center">
  <img src="https://github.com/user-attachments/assets/0cd97aa9-4edb-4479-8aa5-04aa26601fda" width="19%" />
  <img src="https://github.com/user-attachments/assets/31b2d09d-7f6b-4535-aaf5-7b4f31a541cf" width="19%" />
  <img src="https://github.com/user-attachments/assets/050e7ba6-96bc-47f1-bcb9-e55c6f8965b6" width="19%" />
  <img src="https://github.com/user-attachments/assets/e30b3b38-ca96-4bb7-99d7-7344373ad52b" width="19%" />
  <img src="https://github.com/user-attachments/assets/79d1d713-6ee8-4d0e-b1cf-fa887e9d68c2" width="19%" />
</p>


