## 测试

- 启动应用

- 访问 [http://localhost:8083/graphiql](http://localhost:8080/graphiql)


#### 查询

- 查询列表

```graphql
{
  posts {
    id
    title
    content
    createDate
  }
}
```

```json
{
  "data": {
    "posts": [
      {
        "id": "5c50245b7ed65eacb3372aba",
        "title": "Post one",
        "content": "Content of Post one",
        "createDate": "Tue Jan 29 18:00:59 CST 2019"
      },
      {
        "id": "5c50245b7ed65eacb3372abb",
        "title": "Post two",
        "content": "Content of Post two",
        "createDate": "Tue Jan 29 18:00:59 CST 2019"
      }
    ]
  }
}
```

- 查询指定 id

```graphql
{
  post(id: "5c50245b7ed65eacb3372aba") {
    id
    title
    content
    createDate
  }
}
```

```json
{
  "data": {
    "post": {
      "id": "5c50245b7ed65eacb3372aba",
      "title": "Post one",
      "content": "Content of Post one",
      "createDate": "Tue Jan 29 18:00:59 CST 2019"
    }
  }
}
```

#### 修改

- 新增

```graphql
mutation {
  createPost(post: {title: "New Posts", content: "New Post Content"}) {
    id
    title
    content
    createDate
  }
}
```

```json
{
  "data": {
    "createPost": {
      "id": "5c5027197ed65eaf47a0854d",
      "title": "New Posts",
      "content": "New Post Content",
      "createDate": "Tue Jan 29 18:12:41 CST 2019"
    }
  }
}
```

- 修改

```graphql
mutation {
  updatePost(id: "5c5027197ed65eaf47a0854d", post: {title: "Update Posts", content: "Update Post Content"}) {
    id
    title
    content
    createDate
  }
}
```

```json
{
  "data": {
    "updatePost": {
      "id": "5c5027197ed65eaf47a0854d",
      "title": "Update Posts",
      "content": "Update Post Content",
      "createDate": "Tue Jan 29 18:12:41 CST 2019"
    }
  }
}
```

- 删除

```graphql
mutation {
  deletePost(id: "5c5027197ed65eaf47a0854d")
}
```

```json
{
  "data": {
    "deletePost": "5c5027197ed65eaf47a0854d"
  }
}
```

--- 

### 参考文章

- [GraphQL](http://graphql.cn/)
- [Spring Boot + GraphQL + MongoDB](https://medium.com/oril/spring-boot-graphql-mongodb-8733002b728a)
- [graphql-spring-boot](https://github.com/graphql-java-kickstart/graphql-spring-boot)
- [learn-graphql](https://github.com/zhouyuexie/learn-graphql)
- [graphql-mongodb-server](https://github.com/leonardomso/graphql-mongodb-server)