namespace java com.zada.hackathon.gen

// thrift -gen python data.thrift
// thrift -gen java data.thrift

struct Keyword{
  1: string word,
  2: i32 count,
}

struct KeywordRequest{
  1: string word,
  2: i32 number,
}

struct KeywordResponse{
  1: list<Keyword> words,
}

service DataService{

  KeywordResponse searchKeyword(1: KeywordRequest req)//查词

}


