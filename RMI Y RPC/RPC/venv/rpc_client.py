import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://localhost:9000")

num = proxy.add(56,78)


print(num)