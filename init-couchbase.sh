curl -u Administrator:password -v -X POST http://localhost:8091/nodes/self/controller/settings -d 'path=%2Fopt%2Fcouchbase%2Fvar%2Flib%2Fcouchbase%2Fdata&' \
  -d 'index_path=%2Fopt%2Fcouchbase%2Fvar%2Flib%2Fcouchbase%2Fdata&' \
  -d 'cbas_path=%2Fopt%2Fcouchbase%2Fvar%2Flib%2Fcouchbase%2Fdata&' \
  -d 'eventing_path=%2Fopt%2Fcouchbase%2Fvar%2Flib%2Fcouchbase%2Fdata&'
curl  -u Administrator:password -v -X POST http://localhost:8091/node/controller/setupServices \
  -d 'services=kv%2Cn1ql%2Cindex'
curl -u Administrator:password -v -X POST http://localhost:8091/pools/default \
  -d 'memoryQuota=1000' \
  -d 'indexMemoryQuota=1000' \
  -d 'ftsMemoryQuota=1000'
curl -u Administrator:password -v -X POST http://localhost:8091/settings/web -d 'password=password&username=balance&port=SAME'
curl  -u balance:password -v -X POST http://localhost:8091/pools/default/buckets \
  -d 'flushEnabled=1&threadsNumber=3&replicaIndex=0&replicaNumber=0&evictionPolicy=valueOnly&ramQuotaMB=256&bucketType=membase&name=balance'