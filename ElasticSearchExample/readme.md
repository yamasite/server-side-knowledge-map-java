
elasticSearch  2.4.0 版本

plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v1.10.0/elasticsearch-analysis-ik-1.10.0.zip

head地址
http://39.106.208.144:9200/_plugin/head/


docker exec -it jdmelasticsearch /bin/bash
cd bin/
./plugin install mobz/elasticsearch-head

#安装ik分词器  elastSearch 2.4.0对应ik 版本为 1.10.0

./plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v1.10.0/elasticsearch-analysis-ik-1.10.0.zip

docker exec -it jdmelasticsearch /bin/bash
find / -name   analysis-ik

#然后将搜索到的文件夹 复制到  /usr/share/elasticsearch/config/ 下面
cp  -r /etc/elasticsearch/analysis-ik  /usr/share/elasticsearch/config/

# 修改文件夹所属权限  analysis-ik 
cd /usr/share/elasticsearch/config/

chown  -R  elasticsearch.elasticsearch  *

# 重启docker 服务
docker restart  dockerId


中文分词
http://39.106.208.144:9200/_analyze?pretty&analyzer=standard&text="中国话人民工行卡"

ik 分词
http://39.106.208.144:9200/_analyze?pretty&analyzer=ik_max_word&text="中国话人民工行卡"