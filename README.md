# ICD-10 Code Service

Монгол хэл дээрх **ICD-10** (Өвчний олон улсын ангилал, 10-р хувилбар) кодын лавлахыг REST API хэлбэрээр олгодог Spring Boot сервис.

> A Spring Boot REST service that exposes the Mongolian ICD-10 (International Classification of Diseases, 10th revision) reference data over HTTP.

---

## Товч танилцуулга

Эрүүл мэндийн мэдээллийн системүүд өвчний оношийг стандартчилсан ICD-10 кодоор бүртгэдэг. Энэхүү сервис нь тус ангиллын бүх бүтцийг (бүлэг → ангилал → дэд ангилал → дэд код) уншиж, бусад системүүд ашиглах боломжтой JSON API болгон олгоно.

Өгөгдөл нь **22 бүлэг** (chapter), тэдгээрийн доор олон түвшний ангилал агуулсан бөгөөд `src/main/resources/icd10_code.json` файлд хадгалагдсан.

---

## Технологийн стек

| Технологи | Хувилбар / тайлбар |
|---|---|
| Java | 21 |
| Spring Boot | 4.0.3 |
| Maven | Maven Wrapper (`mvnw`) багтсан |
| Lombok | Boilerplate код багасгах |
| Jackson | JSON parse хийх |

---

## Архитектур

Төсөл нь классик **layered architecture** (давхаргат архитектур)-ыг баримталсан:

```
ICD10Controller   (REST давхарга — HTTP хүсэлт хүлээн авах)
      ↓
ICD10Service      (бизнес логик — хайлт, шүүлт)
      ↓
ICD10Repository   (өгөгдлийн давхарга — JSON файлаас унших)
```

**Domain загварууд** (бүгд Java `record` — immutable):

```
Chapter (chapter, name, range, categories)
  └── Category (range, name, subcategories)
        └── Subcategory (code, name, subcode)
              └── Subcode (code, name, detail)
```

---

## Ажиллуулах заавар

### Шаардлага
- JDK 21 ба түүнээс дээш
- Maven (эсвэл төсөлд багтсан `mvnw` wrapper ашиглана)

### Алхмууд

```bash
# 1. Repository-г татаж авах
git clone https://github.com/Aydana06/icd10-code-service.git
cd icd10-code-service

# 2. Ажиллуулах (Linux / macOS)
./mvnw spring-boot:run

# Windows дээр:
mvnw.cmd spring-boot:run
```

Сервис амжилттай эхэлмэгц дараах хаягаар бэлэн болно:

```
http://localhost:8082
```

> Портыг `src/main/resources/application.properties` файлын `server.port` утгаар өөрчилж болно.

### Build хийх

```bash
./mvnw clean package
java -jar target/icd10-service-0.0.1-SNAPSHOT.jar
```

---

## API Endpoints

### 1. Бүх бүлгийн жагсаалт авах

```http
GET /api/icd10
```

**Хариу (200 OK):**

```json
[
  {
    "chapter": "I",
    "name": "Халдварт ба шимэгчит зарим өвчин",
    "range": "A00-B99",
    "categories": [
      {
        "range": "A00-A09",
        "name": "Гэдэсний халдварт өвчин",
        "subcategories": [
          {
            "code": "A00",
            "name": "Урвах тахал",
            "subcode": [
              {
                "code": "A00.0",
                "name": "Урвах тахлын вибрион 01, cholerae биовараар сэдээгдсэн халдвар",
                "detail": "Сонгодог хэлбэрийн урвах тахал"
              }
            ]
          }
        ]
      }
    ]
  }
]
```

### 2. Тодорхой бүлгийг кодоор хайх

```http
GET /api/icd10/{code}
```

| Параметр | Төрөл | Тайлбар | Жишээ |
|---|---|---|---|
| `code` | String | Бүлгийн ромбо код | `I`, `II`, `XV` |

**Жишээ хүсэлт:**

```bash
curl http://localhost:8082/api/icd10/I
```

**Хариу (200 OK):** тухайн нэг бүлгийн бүтэн объект (дээрх бүтэцтэй ижил).

---

## Төслийн бүтэц

```
src/
├── main/
│   ├── java/mn/edu/num/icd10_service/
│   │   ├── Icd10ServiceApplication.java    # Application entry point
│   │   ├── controller/
│   │   │   └── ICD10Controller.java        # REST endpoints
│   │   ├── service/
│   │   │   └── ICD10Service.java           # Бизнес логик
│   │   ├── repository/
│   │   │   └── ICD10Repository.java        # JSON өгөгдөл унших
│   │   └── domain/
│   │       ├── Chapter.java
│   │       ├── Category.java
│   │       ├── Subcategory.java
│   │       ├── Subcode.java
│   │       └── ICD10Wrapper.java
│   └── resources/
│       ├── application.properties
│       └── icd10_code.json                 # ICD-10 өгөгдлийн эх сурвалж
└── test/
    └── java/mn/edu/num/icd10_service/
        └── Icd10ServiceApplicationTests.java
```

---

## Цаашид хөгжүүлэх төлөвлөгөө

- [ ] JSON өгөгдлийг эхлэлд нэг удаа санах ойд ачаалж, хүсэлт бүрд дахин унших шаардлагагүй болгох (caching)
- [ ] Код олдоогүй тохиолдолд `404 Not Found` зөв буцаах (одоогоор `null` буцаадаг)
- [ ] Код болон нэрээр хайх (search) endpoint нэмэх
- [ ] Unit болон integration тест нэмэх
- [ ] Swagger / OpenAPI баримтжуулалт нэмэх
- [ ] Docker container болгох

---

## Зохиогч

**Болат Айдана** — [GitHub](https://github.com/Aydana06)