Key-Value Database
===


---

List all entries:

```shell
curl.exe -X GET http://localhost:8080/kv ; echo
````

---

Get Entry:

```shell
curl.exe -X GET http://localhost:8080/kv/name ; echo
```

---

Insert entry:

```shell
curl.exe -X POST http://localhost:8080/kv/xristos -d "it2024112@hua.gr" ; echo
```

---

Update entry:

```shell
curl.exe -X PATCH http://localhost:8080/kv/version -H "Content-Type: text.plain" -d "0.1.1" ; echo
```

---

Upsert entry:

```shell
curl.exe -X PUT http://localhost:8080/kv/version -H "Content-Type: text.plain" -d "0.1.1" ; echo
```

---
 DeleteEntry:

```shell
curl.exe -X DELETE http://localhost:8080/kv/foo ; echo
```

---


